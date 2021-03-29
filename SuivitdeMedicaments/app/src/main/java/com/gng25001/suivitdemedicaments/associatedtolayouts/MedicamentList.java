package com.gng25001.suivitdemedicaments.associatedtolayouts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gng25001.suivitdemedicaments.R;
import com.gng25001.suivitdemedicaments.database.AppDatabase;
import com.gng25001.suivitdemedicaments.database.MedicamentDAO;
import com.gng25001.suivitdemedicaments.database.NameList;

import java.util.List;

/**
 * Class which run in the background when the layout medicament_list is on screen
 * @author Charles-Antoine
 * 28/03/2021
 */
public class MedicamentList extends AppCompatActivity {

    //**********************************************************************************************
    //VARIABLES
    //list with the names of all the medication saved
    private List<String> names;

    //button to create new medication
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

        int i = 0;
        //loop through all the names
        for (String name : names) {
            //create a text view for the name
            TextView textView = new TextView(this);
            textView.setText(name);
            textView.setTextSize(24);

            //create a button to delete the medication
            Button btnDelete = new Button(this);
            btnDelete.setText(R.string.delete_medication);
            btnDelete.setId(i);
            btnDelete.setTextSize(24);
            btnDelete.setOnClickListener(v -> {
                //create an alert dialog for the user to confirm that he wants to
                //delete the medication
                AlertDialog.Builder builder = new AlertDialog.Builder(MedicamentList.this);
                builder.setCancelable(true);
                builder.setTitle("Delete medication?");
                builder.setPositiveButton("Confirm",
                        (dialog, which) -> {
                            //get the name of the medication to delete
                            String name1 = names.get(v.getId());

                            //get the database and the DAO
                            AppDatabase appDatabase = AppDatabase.getDatabase(getApplicationContext());
                            MedicamentDAO medicamentDAO = appDatabase.medicamentDAO();

                            //load the medication from the DAO then delete it
                            medicamentDAO.deleteMedicament(
                                    medicamentDAO.loadMediament(name1)
                            );

                            //reset the interface
                            startActivity(getIntent());
                            finish();
                            overridePendingTransition(0, 0);

                        });
                builder.setNegativeButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //DO NOTHING
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            });

            //create a button to modify the medication
            Button btnModify = new Button(this);
            btnModify.setText(R.string.modify_medication);
            btnModify.setId(i++);
            btnModify.setTextSize(24);
            btnModify.setOnClickListener(v -> {
                //add the medication name to the intent and then start the activity with it
                Intent intent = new Intent(getApplicationContext(), ModifyMedication.class);
                intent.putExtra("MedicationName", name);
                startActivity(intent);
            });

            //add the new elements to the layout
            linearLayout.addView(textView);
            linearLayout.addView(btnDelete);
            linearLayout.addView(btnModify);
        }
    }
    //END OF PRIVATE METHODS
    //**********************************************************************************************

    //**********************************************************************************************
    //PROTECTED METHODS

    /**
     * Method executed after coming back from the new medication or the modify medication.  It
     * reset the layout to update the medication list
     */
    @Override
    protected void onRestart() {
        super.onRestart();

        //restart the activity
        startActivity(getIntent());
        finish();
        overridePendingTransition(0, 0);
    }
    //END OF PROTECTED METHODS
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
    //END OF PUBLIC METHODS
    //**********************************************************************************************
}
