package com.example.kioskita2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "item_event")
public class itemEvent {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String judul;
    public String kategori;
    public int eventpic;

    public itemEvent(String judul, String kategori, int eventpic) {
        this.judul = judul;
        this.kategori = kategori;
        this.eventpic = eventpic;
    }

    // Getters and Setters
    public int getId() {
        return id; }
    public void setId(int id) {
        this.id = id; }
    public String getJudul() {
        return judul; }
    public void setJudul(String judul) {
        this.judul = judul; }
    public String getKategori() {
        return kategori; }
    public void setKategori(String kategori) {
        this.kategori = kategori; }
    public int getEventpic() {
        return eventpic; }
    public void setEventpic(int eventpic) {
        this.eventpic = eventpic; }
}


