package com.gng25001.suivitdemedicaments;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Alarm {

    //**********************************************************************************************
    //VARIABLES
    @ColumnInfo(name="days")
    boolean[] selectedDays = {true, false, false, false, false, false, false};

    @PrimaryKey
    @ColumnInfo(name="nameAndTime")
    String medicationNameAndTime;
    //END OF VARIABLES
    //**********************************************************************************************

    //**********************************************************************************************
    //CONSTRUCTOR
    /**
     * Saves the data used to create a pending intent
     * @param hours the hour the alarm must trigger
     * @param minutes the minute the alarm must trigger
     * @param selectedDays the days the alarm must trigger
     * @param name the name of the medication associated
     */
    public Alarm(short hours, short minutes, boolean[] selectedDays, String name) {
        this.selectedDays = selectedDays;
        this.medicationNameAndTime = name +":" + Short.toString(hours) + ":" + Short.toString(minutes);
    }
    //END OF CONSTRUCTOR
    //**********************************************************************************************

    //**********************************************************************************************
    //GETTERS
    public short getHours() {
        String[] array = medicationNameAndTime.split(":");
        return Short.parseShort(array[1]);
    }
    public short getMinutes() {
        String[] array = medicationNameAndTime.split(":");
        return Short.parseShort(array[2]);
    }
    public String getAlarmName() {
        String[] array = medicationNameAndTime.split(":");
        return array[0];
    }
    //END OF GETTERS
    //**********************************************************************************************

    //**********************************************************************************************
    //SETTERS
    public void setHours(short hours) {
        String[] array = medicationNameAndTime.split(":");
        medicationNameAndTime = array[0] + hours + array[2];
    }
    public void setMinutes(short minutes) {
        String[] array = medicationNameAndTime.split(":");
        medicationNameAndTime = array[0] + array[1] + minutes;
    }
    public void setAlarmName(String name) {
        String[] array = medicationNameAndTime.split(":");
        medicationNameAndTime = name + array[1] + array[2];
    }
    //END OF SETTERS
    //**********************************************************************************************
}
