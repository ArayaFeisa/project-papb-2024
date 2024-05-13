package com.example.kioskitalocal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MateriDAO {
    @Insert
    void addMateri(MateriModel materi);

    @Update
    void updateMateri(MateriModel materi);

    @Delete
    void deleteMateri(MateriModel materi);

    @Query("SELECT * FROM materi")
    List<MateriModel> getAllMateri();

    @Query("SELECT * FROM materi WHERE materi_id = :materi_id")
    MateriModel getMateri(int materi_id);

    @Query("SELECT COUNT(*) FROM materi")
    int countMateri();
}
