package com.gng25001.suivitdemedicaments;

import android.app.Application;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Medicament {

    //**********************************************************************************************
    //VARIABLES
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "names")
    private String name;

    @ColumnInfo(name = "doses")
    private short dose;

    @ColumnInfo(name = "waiting_time")
    private short waitingTimeBeforeNewStockDays;

    @ColumnInfo(name = "prescription_size")
    private short prescriptionSize;

    @ColumnInfo(name = "total_of_medicament")
    private int totalOfMedicament;
    //END OF VARIABLES
    //**********************************************************************************************

    //**********************************************************************************************
    //CONSTRUCTORS
    /**
     * Complete constructor for Medicament
     * @param name name of medicament
     * @param dose preset quantity to be taken
     * @param waitingTimeBeforeNewStockDays necessary time before new order to pharmacy
     * @param prescriptionSize given quantity by pharmacy
     * @param totalOfMedicament current total of medicament
     */
    public Medicament(String name,short dose, short waitingTimeBeforeNewStockDays,
                      short prescriptionSize, int totalOfMedicament) {
        this.name = name;
        this.dose = dose;
        this.waitingTimeBeforeNewStockDays = waitingTimeBeforeNewStockDays;
        this.prescriptionSize = prescriptionSize;
        this.totalOfMedicament = totalOfMedicament;

    }
    //END OF CONSTRUCTORS
    //**********************************************************************************************

    //**********************************************************************************************
    //GETTERS
    public String getName() {return name;}
    public short getDose() {return dose;}
    public short getWaitingTimeBeforeNewStockDays() {return waitingTimeBeforeNewStockDays;}
    public short getPrescriptionSize() {return prescriptionSize;}
    public int getTotalOfMedicament() {return totalOfMedicament;}
    //END OF GETTERS
    //**********************************************************************************************

    //**********************************************************************************************
    //SETTERS
    public void setName(String name) {this.name = name;}
    public void setDose(short dose) {this.dose = dose;}
    public void setWaitingTimeBeforeNewStockDays(short waitingTimeBeforeNewStockDays) {
        this.waitingTimeBeforeNewStockDays = waitingTimeBeforeNewStockDays;
    }
    public void setPrescriptionSize(short prescriptionSize) {this.prescriptionSize = prescriptionSize;}
    public void setTotalOfMedicament(int totalOfMedicament) {this.totalOfMedicament = totalOfMedicament;}
    //END OF SETTERS
    //**********************************************************************************************
}
