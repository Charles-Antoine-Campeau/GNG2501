package com.gng25001.suivitdemedicaments;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Medicament {

    //**********************************************************************************************
    //VARIABLES
    @PrimaryKey
    @ColumnInfo(name = "names")
    private String name;

    @ColumnInfo(name = "images")
    private Bitmap image;

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
     * @param image image of medicament
     * @param dose preset quantity to be taken
     * @param waitingTimeBeforeNewStockDays necessary time before new order to pharmacy
     * @param prescriptionSize given quantity by pharmacy
     * @param totalOfMedicament current total of medicament
     */
    public Medicament(String name, Bitmap image, short dose, short waitingTimeBeforeNewStockDays,
                      short prescriptionSize, int totalOfMedicament) {
        this.name = name;
        this.image = image;
        this.dose = dose;
        this.waitingTimeBeforeNewStockDays = waitingTimeBeforeNewStockDays;
        this.prescriptionSize = prescriptionSize;
        this.totalOfMedicament = totalOfMedicament;

    }

    /**
     * Constructor without image
     * @param name name of medicament
     * @param dose preset quantity to be taken
     * @param waitingTimeBeforeNewStockDays necessary time before new order to pharmacy
     * @param prescriptionSize given quantity by pharmacy
     * @param totalOfMedicament current total of medicament
     */
    public Medicament(String name, short dose, short waitingTimeBeforeNewStockDays,
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
    public Bitmap getImage() {return image;}
    public short getDose() {return dose;}
    public short getWaitingTimeBeforeNewStockDays() {return waitingTimeBeforeNewStockDays;}
    public short getPrescriptionSize() {return prescriptionSize;}
    public int getTotalOfMedicament() {return totalOfMedicament;}
    //END OF GETTERS
    //**********************************************************************************************

    //**********************************************************************************************
    //SETTERS


}
