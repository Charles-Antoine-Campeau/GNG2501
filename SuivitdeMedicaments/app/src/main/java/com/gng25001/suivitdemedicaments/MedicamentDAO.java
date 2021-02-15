package com.gng25001.suivitdemedicaments;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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
    public LiveData<List<Medicament>> loadAllMedicament();

    @Query("SELECT names FROM Medicament")
    public LiveData<List<Medicament>> loadAllNames();
}
