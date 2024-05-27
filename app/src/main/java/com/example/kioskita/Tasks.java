package com.example.kioskita;

public class Tasks {
    private String judul;
    private String bab;
    private String deskripsi;
    private String imageUrl;

    public Tasks() {

    }

    public Tasks(String judul, String bab, String deskripsi) {
        this.judul = judul;
        this.bab = bab;
        this.deskripsi = deskripsi;
    }

    public String getJudul() { return judul; }
    public void setJudul(String judul) { this.judul = judul; }
    public String getBab() { return bab; }
    public void setBab(String bab) { this.bab = bab; }
    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
