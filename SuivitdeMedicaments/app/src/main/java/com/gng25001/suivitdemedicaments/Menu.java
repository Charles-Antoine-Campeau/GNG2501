package com.gng25001.suivitdemedicaments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {

    //Elements of the layout
    private Button btnMedList;
    private Button btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        //Set the layout as the menu
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        getLayoutElements();
    }

    /**
     * Get the elements from the layout
     */
    private void getLayoutElements(){
        btnMedList = findViewById(R.id.btnMenuGoToMedList);
        btnSettings = findViewById(R.id.btnMenuSettings);
    }


    /**
     * Function called by every button on the menu activity
     * @param view the item that was clicked
     */
    public void onClick(View view){

        
    }
}