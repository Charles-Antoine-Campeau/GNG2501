package com.gng25001.suivitdemedicaments.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.gng25001.suivitdemedicaments.Medicament;

import java.util.List;

@Dao
public interface MedicamentDAO {

    @Insert
    public void insertNewMedicament(Medicament medicament);

    @Update
    public void updateMedicament(Medicament medicament);

    @Delete
    public void deleteMedicament(Medicament medicament);

    @Query("SELECT * FROM Medicament")
    public List<Medicament> loadAllMedicament();

    @Query("SELECT names FROM Medicament")
    public List<String> loadAllNames();

    @Query("SELECT * FROM Medicament WHERE names LIKE :name")
    public Medicament loadMediament(String name);
}
