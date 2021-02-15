package com.gng25001.suivitdemedicaments;

import android.content.Context;

import java.util.List;

/**
 * Singleton class to access all the names in the database
 */
public class NameList {

    //**********************************************************************************************
    //VARIABLES
    //Instance for singleton
    private static volatile NameList nameList;

    private List<String> names;
    //END OF VARIABLES
    //**********************************************************************************************

    //**********************************************************************************************
    //CONSTRUCTORS
    private NameList(Context applicationContext) {
        loadNames(applicationContext);
    }
    //END OF CONSTRUCTORS
    //**********************************************************************************************

    //**********************************************************************************************
    //

    /**
     * Get the singleton instance of NameList
     * @param applicationContext
     * @return the instance
     */
    public static NameList getInstance(Context applicationContext) {
        if (nameList == null) {
            nameList = new NameList(applicationContext);
        }
        return nameList;
    }

    /**
     * Method to get a list of all the names
     * @param applicationContext
     * @return A list with all the names
     */
    public List<String> getNames(Context applicationContext) {
        loadNames(applicationContext);
        return names;
    }

    /**
     * Method to load all the names from the database
     * @param applicationContext
     */
    private void loadNames (Context applicationContext) {
        AppDatabase appDatabase = AppDatabase.getDatabase(applicationContext);
        MedicamentDAO medicamentDAO = appDatabase.medicamentDAO();
        names = medicamentDAO.loadAllNames();
    }
    //
    //**********************************************************************************************
}
