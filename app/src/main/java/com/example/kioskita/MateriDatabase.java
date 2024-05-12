package com.example.kioskita;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MateriModel.class},version = 1)
public abstract class MateriDatabase extends RoomDatabase {
    public abstract MateriDAO getMateriDAO();
}

