package com.gng25001.suivitdemedicaments;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Medicament {

    //Variable defining a medicament
    @ColumnInfo(name = "images")
    private Bitmap image;

    @PrimaryKey
    private String name;

    @ColumnInfo(name = "doses")
    private short dose;

    @ColumnInfo(name = "waiting_time")
    private short waitingTimeBeforeNewStockDays;

    @ColumnInfo(name = "prescription_size")
    private short prescriptionSize;

    @ColumnInfo(name = "total_of_medicament")
    private int totalOfMedicament;

    public Medicament(){

    }
}
