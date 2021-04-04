package com.gng25001.suivitdemedicaments.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.gng25001.suivitdemedicaments.Alarm;

import java.util.List;

@Dao
public interface AlarmDAO {

    @Insert
    public void insertNewAlarm(Alarm alarm);

    @Update
    public void updateAlarm(Alarm alarm);

    @Delete
    public void deleteAlarm(Alarm alarm);

    @Query("SELECT * FROM Alarm")
    public List<Alarm> loadAllAlarms();

    @Query("SELECT name FROM Alarm")
    public List<String> loadMedicationName();

    @Query("SELECT * FROM Alarm WHERE notificationID LIKE :notificationID")
    public Alarm loadAlarm(int notificationID);
}
