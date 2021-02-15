package com.gng25001.suivitdemedicaments.database;

import android.app.Application;

import com.gng25001.suivitdemedicaments.Medicament;
import com.gng25001.suivitdemedicaments.database.AppDatabase;
import com.gng25001.suivitdemedicaments.database.MedicamentDAO;

import java.util.List;

public class MedicamentRepository {

    private MedicamentDAO medicamentDAO;
    private List<Medicament> allMedicaments;
    private List<String> allMedicamentsNames;

    public MedicamentRepository(Application application) {

        AppDatabase db = AppDatabase.getDatabase(application);
        medicamentDAO = db.medicamentDAO();
        allMedicaments = medicamentDAO.loadAllMedicament();
        allMedicamentsNames = medicamentDAO.loadAllNames();
    }

    public List<Medicament> getAllMedicaments() {
        return allMedicaments;
    }

    public void insert(Medicament medicament) {
        AppDatabase.databaseWriteExecutor.execute(() -> {medicamentDAO.insertNewMedicament(medicament);});
    }
}
