package com.harun.al.rosyid.eskripsi.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
@IgnoreExtraProperties
public class Ta implements Serializable {

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private String tahun;
    private String nim;
    private String nama;
    private String judul;
    private String prodi;
    private String key;

    public Ta(){

    }

    public Ta(String tahun, String nim, String nama, String judul, String prodi){

        this.tahun = tahun;
        this.nim = nim;
        this.nama = nama;
        this.judul = judul;
        this.prodi = prodi;


    }

}
