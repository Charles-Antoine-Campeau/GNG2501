package com.gng25001.suivitdemedicaments.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.gng25001.suivitdemedicaments.Medicament;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Medicament.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    /**
     * Expose the MedicamentDAO
     * @return an instance of MedicamentDAO
     */
     abstract MedicamentDAO medicamentDAO();

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
    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if(INSTANCE == null) {
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
