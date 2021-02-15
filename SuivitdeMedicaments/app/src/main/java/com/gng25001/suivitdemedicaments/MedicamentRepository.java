package com.gng25001.suivitdemedicaments;

import android.app.Application;

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
