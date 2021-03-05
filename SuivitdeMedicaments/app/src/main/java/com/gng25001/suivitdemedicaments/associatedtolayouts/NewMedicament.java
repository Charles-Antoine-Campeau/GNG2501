package com.gng25001.suivitdemedicaments.associatedtolayouts;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.gng25001.suivitdemedicaments.R;

public class NewMedicament extends AppCompatActivity {

    //**********************************************************************************************
    //VARIABLES
    private Button btnCancel;
    private Button btnSave;
    //END OF VARIABLES
    //**********************************************************************************************

    //**********************************************************************************************
    //ONCREATE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set view as associated layout
        setContentView(R.layout.new_medicament);

        //get the cancel and save buttons
        btnCancel = findViewById(R.id.btnCancelNewMed);
        btnSave = findViewById(R.id.btnSaveNewMed);
    }
    //END OF ONCREATE
    //**********************************************************************************************

    public void onClick(View view) {
        //determine which button was pressed
        if (view == btnCancel) {
            //discard all changes and goes back to the medication list
            finish();
        }
    }
}
