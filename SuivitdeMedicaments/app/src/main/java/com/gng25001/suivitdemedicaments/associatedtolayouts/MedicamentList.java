package com.gng25001.suivitdemedicaments.associatedtolayouts;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gng25001.suivitdemedicaments.R;
import com.gng25001.suivitdemedicaments.database.NameList;

import java.util.Iterator;
import java.util.List;

/**
 * Class which run in the background when the layout medicament_list is on screen
 */
public class MedicamentList extends AppCompatActivity {

    //**********************************************************************************************
    //VARIABLES
    //list with the names of all the medicament saved
    private List<String> names;
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
        //get the scroll view where the elements will be added
        ScrollView scrollView = findViewById(R.id.scrvMedicamentList);

        //loop through all the names
        for (String name : names) {
            //create a text view for the name
            TextView textView = new TextView(this);
            textView.setText(name);

            //add the text view to the layout
            scrollView.addView(textView);
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
        
    }
}
