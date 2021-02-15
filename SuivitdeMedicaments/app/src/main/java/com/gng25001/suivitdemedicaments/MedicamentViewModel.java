package com.gng25001.suivitdemedicaments;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MedicamentViewModel extends AndroidViewModel {

    private MedicamentRepository medicamentRepository;

    private final LiveData<List<Medicament>> allMedicament;

    public MedicamentViewModel (Application application) {
        super(application);
        medicamentRepository = new MedicamentRepository(application);
        allMedicament = medicamentRepository.getAllMedicaments();
    }

    LiveData<List<Medicament>> getAllMedicament() {return allMedicament;}

    public void insert(Medicament medicament) { medicamentRepository.insert(medicament);}
}
