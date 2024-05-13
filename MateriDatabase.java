package com.example.kioskitalocal;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MateriModel.class}, version = 1)
public abstract class MateriDatabase extends RoomDatabase {
    private static MateriDatabase instance;

    public abstract MateriDAO materiDAO();

    public static synchronized MateriDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            MateriDatabase.class, "materi_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}

