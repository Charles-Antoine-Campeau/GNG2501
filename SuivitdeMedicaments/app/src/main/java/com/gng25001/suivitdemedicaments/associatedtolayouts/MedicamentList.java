package com.gng25001.suivitdemedicaments.associatedtolayouts;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gng25001.suivitdemedicaments.R;
import com.gng25001.suivitdemedicaments.database.NameList;

import java.util.List;

/**
 * Class which run in the background when the layout medicament_list is on screen
 */
public class MedicamentList extends AppCompatActivity {

    //**********************************************************************************************
    //VARIABLES
    //list with the names of all the medicament saved
    private List<String> names;

    private Button newButton;
    //END OF VARIABLES
    //**********************************************************************************************

    //**********************************************************************************************
    //ONCREATE
    /**
     * First method called of the class
     * @param savedInstanceState app info
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //set view as associated layout
        setContentView(R.layout.medicament_list);

        //get the view of the new button
        newButton = findViewById(R.id.btnNew);

        //get the names from the database
        getNames();
    }
    //END OF ONCREATE
    //**********************************************************************************************

    //**********************************************************************************************
    //PRIVATE METHODS
    /**
     * Get all of the names from the database
     */
    private void getNames() {
        //get NameList singleton
        NameList nameList = NameList.getInstance(getApplicationContext());

        //get the list of names
        names = nameList.getNames(getApplicationContext());

        addList();
    }

    /**
     * Add the list of names to the screen
     */
    private void addList() {
        //get the linear layout where the elements will be added
        LinearLayout linearLayout = findViewById(R.id.LLMedicamentListList);

        int i = 1;
        //loop through all the names
        for (String name : names) {
            //create a text view for the name
            TextView textView = new TextView(this);
            textView.setText(name);
            textView.setTextSize(24);

            Button btnDelete = new Button(this);
            btnDelete.setText(R.string.delete_medication);
            btnDelete.setId(i++);
            btnDelete.setTextSize(24);

            Button btnModify = new Button(this);
            btnModify.setText(R.string.modify_medication);
            btnModify.setId(i++);
            btnModify.setTextSize(24);

            //add the new elements to the layout
            linearLayout.addView(textView);
            linearLayout.addView(btnDelete);
            linearLayout.addView(btnModify);
        }



    }
    //END OF PRIVATE METHODS
    //**********************************************************************************************

    //**********************************************************************************************
    //PUBLIC METHODS
    /**
     * Function called by every button on the medicament_list activity
     * @param view the item that was clicked
     */
    public void onClick(View view) {
        //determine which button was pressed
        if (view == newButton) {
            //open the new medicament layout
            startActivity(new Intent(getApplicationContext(), NewMedicament.class));
        }
    }
}
