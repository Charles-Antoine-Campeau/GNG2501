package com.gng25001.suivitdemedicaments.associatedtolayouts;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.gng25001.suivitdemedicaments.R;

public class NewMedicament extends AppCompatActivity {

    //**********************************************************************************************
    //ONCREATE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set view as associated layout
        setContentView(R.layout.new_medicament);
    }

    public void onClick(View view) {

    }
}
