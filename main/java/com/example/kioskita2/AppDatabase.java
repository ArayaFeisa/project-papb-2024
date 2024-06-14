package com.example.kioskita2;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {itemEvent.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ItemEventDao itemEventDao();

    private static volatile AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "item_event_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

