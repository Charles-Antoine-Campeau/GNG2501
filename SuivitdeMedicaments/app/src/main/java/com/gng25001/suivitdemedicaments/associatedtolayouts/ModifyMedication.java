package com.gng25001.suivitdemedicaments.associatedtolayouts;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gng25001.suivitdemedicaments.Medicament;
import com.gng25001.suivitdemedicaments.R;
import com.gng25001.suivitdemedicaments.database.AppDatabase;
import com.gng25001.suivitdemedicaments.database.MedicamentDAO;

public class ModifyMedication extends AppCompatActivity {

    //**********************************************************************************************
    //VARIABLES
    private Button btnCancel;
    private Button btnSave;
    private TextView etxtName;
    private EditText etxtnDoses;
    private EditText etxtnWaitingTimeNewDoses;
    private EditText etxtnQuantityOfDosesInPrescriptions;
    private EditText etxtnCurrentTotal;

    private AppDatabase appDatabase;
    private MedicamentDAO medicamentDAO;
    private Medicament medicament;
    //END OF VARIABLES
    //**********************************************************************************************

    //**********************************************************************************************
    //ONCREATE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get the extra data passed with the intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("MedicationName");

        //set the view
        setContentView(R.layout.modify_medication);

        //get all the elements from the layout
        btnCancel = findViewById(R.id.btnCancelModifyMed);
        btnSave = findViewById(R.id.btnSaveModifiedMed);
        etxtName = findViewById(R.id.etxtNameModify);
        etxtnDoses = findViewById(R.id.etxtnDosesModify);
        etxtnWaitingTimeNewDoses = findViewById(R.id.etxtnWaitingTimeNewDosesModify);
        etxtnQuantityOfDosesInPrescriptions = findViewById(R.id.etxtnQuantityOfDosesInPrescriptionsModify);
        etxtnCurrentTotal = findViewById(R.id.etxtnCurrentTotalModify);

        getMedication(name);
    }
    //END OF ONCREATE
    //**********************************************************************************************

    //**********************************************************************************************
    //PUBLIC METHODS
    /**
     * On click method for the modify medication
     * @param view the object that was clicked
     */
    public void onClickModifyMedication(View view) {

        //cancel the modification
        if (view == btnCancel) {
            finish();
        }

        //saves the modification
        if (view == btnSave) {
            boolean hasError = false;
            //make sure all edit texts have a value
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

            //if no input errors, modify the medicament and saves it in the database
            if (!hasError) {
                medicament.setName(etxtName.getText().toString());
                medicament.setDose(Short.parseShort(etxtnDoses.getText().toString()));
                medicament.setWaitingTimeBeforeNewStockDays(
                        Short.parseShort(etxtnWaitingTimeNewDoses.getText().toString()));
                medicament.setPrescriptionSize(
                        Short.parseShort(etxtnQuantityOfDosesInPrescriptions.getText().toString()));
                medicament.setTotalOfMedicament(
                        Integer.parseInt(etxtnCurrentTotal.getText().toString()));

                //update the medication in the database and end the layout
                medicamentDAO.updateMedicament(medicament);
                finish();
            }
        }
    }
    //END OF PUBLIC METHODS
    //**********************************************************************************************

    //**********************************************************************************************
    //PRIVATE METHODS
    /**
     * Get a specific medication from the database and adds its elements to the corresponding
     * text view of the layout
     * @param name name of the medication to load
     */
    private void getMedication(String name) {
        //get the database and the DAO
        appDatabase = AppDatabase.getDatabase(getApplicationContext());
        medicamentDAO = appDatabase.medicamentDAO();

        //load the medication
        medicament = medicamentDAO.loadMediament(name);

        //add the medication elements to the corresponding text view
        etxtName.setText(medicament.getName());
        etxtnDoses.setText(Short.toString(medicament.getDose()));
        etxtnWaitingTimeNewDoses.setText(Short.toString(medicament.getWaitingTimeBeforeNewStockDays()));
        etxtnQuantityOfDosesInPrescriptions.setText(Short.toString(medicament.getPrescriptionSize()));
        etxtnCurrentTotal.setText(Integer.toString(medicament.getTotalOfMedicament()));
    }
    //END OF PRIVATE METHODS
    //**********************************************************************************************
}
