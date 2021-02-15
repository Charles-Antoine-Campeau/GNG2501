package com.gng25001.suivitdemedicaments;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MedicamentRepository {

    private MedicamentDAO medicamentDAO;
    private LiveData<List<Medicament>> allMedicaments;

    MedicamentRepository(Application application) {

        AppDatabase db = AppDatabase.getDatabase(application);
        medicamentDAO = db.medicamentDAO();
        allMedicaments = medicamentDAO.loadAllMedicament();
    }

    LiveData<List<Medicament>> getAllMedicaments() {
        return allMedicaments;
    }

    void insert(Medicament medicament) {
        AppDatabase.databaseWriteExecutor.execute(() -> {medicamentDAO.insertNewMedicament(medicament);});
    }
}
