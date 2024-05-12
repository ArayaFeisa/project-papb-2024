package com.example.kioskita;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "materi")
public class MateriModel {
    @ColumnInfo(name = "materi_id")
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "judul")
    String judul;
    @ColumnInfo(name = "bab")
    String bab;
    @ColumnInfo(name = "gambar")
    String gambar;
    @ColumnInfo(name = "deskripsi")
    String deskripsi;

    public MateriModel(String judul, String bab, String gambar, String deskripsi) {
        this.judul = judul;
        this.bab = bab;
        this.gambar = gambar;
        this.deskripsi = deskripsi;
        this.id = 0;
    }

    public String getJudul() {
        return judul;
    }

    public String getBab() {
        return bab;
    }

    public String getGambar() {
        return gambar;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setBab(String bab) {
        this.bab = bab;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
