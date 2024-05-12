package com.example.kioskita;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MateriDAO {

    @Insert
    public void addMateri(MateriModel materi);

    @Update
    public void updateMateri(MateriModel materi);

    @Delete
    public void deleteMateri(MateriModel materi);

    @Query("SELECT * FROM materi")
    public List<MateriModel> getAllMateri();

    @Query("SELECT * FROM materi where materi_id==:materi_id")
    public MateriModel getMateri(int materi_id);

    @Query("SELECT COUNT(materi_id) FROM materi")
    int countMateri();

}
