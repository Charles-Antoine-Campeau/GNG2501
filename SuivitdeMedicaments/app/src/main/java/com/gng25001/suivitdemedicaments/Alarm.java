package com.gng25001.suivitdemedicaments;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Alarm {

    //**********************************************************************************************
    //VARIABLES
    @ColumnInfo(name="hour")
    short hour;

    @ColumnInfo(name="minute")
    short minute;

/* TODO
    @ColumnInfo(name="days")
    boolean[] selectedDays;
*/
    @ColumnInfo(name="name")
    String medicationName;

    @PrimaryKey
    @ColumnInfo(name="notificationID")
    int notificationID;
    //END OF VARIABLES
    //**********************************************************************************************

    //**********************************************************************************************
    //CONSTRUCTOR
    /**
     * Saves the data used to create a pending intent
     * @param hour the hour the alarm must trigger
     * @param minute the minute the alarm must trigger
     * @param medicationName the name of the medication associated
     * @param notificationID the ID associated to the pending intent
     */
    public Alarm(short hour, short minute, String medicationName, int notificationID) {
        this.hour = hour;
        this.minute = minute;
        //this.selectedDays = selectedDays;
        this.medicationName = medicationName;
        this.notificationID = notificationID;
    }
    //END OF CONSTRUCTOR
    //**********************************************************************************************

    //**********************************************************************************************
    //GETTERS
    public short getHour() {return hour;}
    public short getMinute() {return minute;}
    //public boolean[] getSelectedDays() {return selectedDays;}
    public String getMedicationName() {return medicationName;}
    public int getNotificationID() {return notificationID;}
    //END OF GETTERS
    //**********************************************************************************************

    //**********************************************************************************************
    //SETTERS
    public void setHour(short hour) {this.hour = hour;}
    public void setMinute(short minute) {this.minute = minute;}
    //public void setSelectedDays(boolean[] selectedDays) {this.selectedDays = selectedDays;}
    public void setMedicationName(String medicationName) {this.medicationName = medicationName;}
    public void setNotificationID(int notificationID) {this.notificationID =notificationID;}
    //END OF SETTERS
    //**********************************************************************************************
}
