package com.example.kioskita2;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ItemEventDao {
    @Insert
    void insert(itemEvent itemEvent);

    @Update
    void update(itemEvent itemEvent);

    @Delete
    void delete(itemEvent itemEvent);

    @Query("SELECT * FROM item_event")
    List<itemEvent> getAllItemEvents();
}
