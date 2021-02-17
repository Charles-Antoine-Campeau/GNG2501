package com.gng25001.suivitdemedicaments.associatedtolayouts;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gng25001.suivitdemedicaments.R;
import com.gng25001.suivitdemedicaments.database.NameList;

import java.util.List;

public class MedicamentList extends AppCompatActivity {

    private List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicament_list);

        getNames();
    }

    private void getNames() {
        NameList nameList = NameList.getInstance(getApplicationContext());
        names = nameList.getNames(getApplicationContext());
    }
}
