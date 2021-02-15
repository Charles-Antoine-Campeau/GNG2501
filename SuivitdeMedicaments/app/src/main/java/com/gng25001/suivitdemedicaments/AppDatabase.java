package com.gng25001.suivitdemedicaments;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Medicament.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    /**
     * Expose the MedicamentDAO
     * @return an instance of MedicamentDAO
     */
    public abstract MedicamentDAO medicamentDAO();

    //Instance for singleton
    private static volatile AppDatabase INSTANCE;

    //Thread pool to execute asynchronous background opperations
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
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "medicament_database").build();
                }
            }
        }
        return INSTANCE;
    }

}
