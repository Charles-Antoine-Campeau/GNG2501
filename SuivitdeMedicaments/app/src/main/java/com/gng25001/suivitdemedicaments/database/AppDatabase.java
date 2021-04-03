package com.gng25001.suivitdemedicaments.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.gng25001.suivitdemedicaments.Medicament;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Database of the application.  Defined as a singleton, with getDatabase for the instance.
 * Does't do much except returning the DAO with the medicamentDAO() method
 * @author Charles-Antoine
 * 16/02/2021
 */
@Database(entities = {Medicament.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    /**
     * Expose the MedicamentDAO
     * @return an instance of MedicamentDAO
     */
     public abstract MedicamentDAO medicamentDAO();

    /**
     * Expose the AlarmDAO
     * @return an instance of AlarmDAO
     */
    public abstract AlarmDAO alarmDAO();

    //Instance for singleton
    private static volatile AppDatabase INSTANCE;

    //Thread pool to execute asynchronous background operations
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /**
     * get the singleton instance of AppDatabase
     * @param context
     * @return the instance
     */
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if(INSTANCE == null) {
                    //*****IMPORTANT*****
                    //Currently gets the data on main thread.  This could cause the app to freeze
                    //or stop working.  It could be run on background with the Executor defined over
                    //but I'm too lazy and not enough a programmer maniac ot care.  Although, I
                    // might change it if I have time or if the app crashes too often.
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "medicament_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
