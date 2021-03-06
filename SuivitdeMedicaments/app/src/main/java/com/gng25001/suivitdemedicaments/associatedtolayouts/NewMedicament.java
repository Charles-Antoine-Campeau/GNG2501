package com.gng25001.suivitdemedicaments.associatedtolayouts;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.gng25001.suivitdemedicaments.Medicament;
import com.gng25001.suivitdemedicaments.R;
import com.gng25001.suivitdemedicaments.database.AppDatabase;
import com.gng25001.suivitdemedicaments.database.MedicamentDAO;

public class NewMedicament extends AppCompatActivity {

    //**********************************************************************************************
    //VARIABLES
    private Button btnCancel;
    private Button btnSave;
    private EditText etxtName;
    private EditText etxtnDoses;
    private EditText etxtnWaitingTimeNewDoses;
    private EditText etxtnQuantityOfDosesInPrescriptions;
    private EditText etxtnCurrentTotal;
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
        btnCancel = findViewById(R.id.btnCancelModifyMed);
        btnSave = findViewById(R.id.btnSaveModifiedMed);

        //get the edit text items
        etxtName = findViewById(R.id.etxtName);
        etxtnDoses = findViewById(R.id.etxtnDoses);
        etxtnWaitingTimeNewDoses = findViewById(R.id.etxtnWaitingTimeNewDoses);
        etxtnQuantityOfDosesInPrescriptions = findViewById(R.id.etxtnQuantityOfDosesInPrescriptions);
        etxtnCurrentTotal = findViewById(R.id.etxtnCurrentTotal);
    }
    //END OF ONCREATE
    //**********************************************************************************************

    //**********************************************************************************************
    //PUBLIC METHODS

    /**
     * On click method for the buttons on the new medicament layout
     * @param view the clicked object
     */
    public void onClickNewMedicament(View view) {
        //discard all changes and goes back to the medication list
        if (view == btnCancel) {

            finish();
        }

        //tries to save the medication
        if (view == btnSave) {
            boolean hasError = false;

            //make sure all edit texts have a value
            if (TextUtils.isEmpty(etxtName.getText().toString())) {
                etxtName.setError("A name is required");
                hasError = true;
            }
            if (TextUtils.isEmpty(etxtnDoses.getText().toString())) {
                etxtnDoses.setError("A dose is required");
                hasError = true;
            }
            if (TextUtils.isEmpty(etxtnWaitingTimeNewDoses.getText().toString())) {
                etxtnWaitingTimeNewDoses.setError("A waiting time si required");
                hasError = true;
            }
            if (TextUtils.isEmpty(etxtnQuantityOfDosesInPrescriptions.getText().toString())) {
                etxtnQuantityOfDosesInPrescriptions.setError("A quantity in each prescriptions is required");
                hasError = true;
            }
            if (TextUtils.isEmpty(etxtnCurrentTotal.getText().toString())) {
                etxtnCurrentTotal.setError("A current total is required");
                hasError = true;
            }

            //if no input errors, create the medicament and saves it in the database
            if (!hasError) {
                Medicament medicament = new Medicament(etxtName.getText().toString(),
                        Short.parseShort(etxtnDoses.getText().toString()),
                        Short.parseShort(etxtnWaitingTimeNewDoses.getText().toString()),
                        Short.parseShort(etxtnQuantityOfDosesInPrescriptions.getText().toString()),
                        Integer.parseInt(etxtnCurrentTotal.getText().toString())
                );

                //get the database then the DAO, saves the medication, then finish the layout
                AppDatabase appDatabase = AppDatabase.getDatabase(getApplicationContext());
                MedicamentDAO medicamentDAO = appDatabase.medicamentDAO();
                medicamentDAO.insertNewMedicament(medicament);
                finish();
            }
        }
    }
    //END OF PUBLIC METHODS
    //**********************************************************************************************
}
