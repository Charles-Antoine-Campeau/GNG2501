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

    @Query("SELECT nameAndTime FROM Alarm")
    public List<String> loadNameAndTime();

    @Query("SELECT * FROM Alarm WHERE nameAndTime LIKE :nameAndTime")
    public Alarm loadAlarm(String nameAndTime);
}
