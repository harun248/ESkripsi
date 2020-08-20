package com.harun.al.rosyid.eskripsi.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Bimbingan implements Serializable {
    private String nim;
    private String nama;
    private String tanggal;
    private String tempat;
    private String pertemuan;

    public String getDosen() {
        return dosen;
    }

    public void setDosen(String dosen) {
        this.dosen = dosen;
    }

    private String dosen;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private String key;
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

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getPertemuan() {
        return pertemuan;
    }

    public void setPertemuan(String pertemuan) {
        this.pertemuan = pertemuan;
    }




    public Bimbingan(){

    }

    public Bimbingan(String tempat, String nim, String nama, String tanggal, String pertemuan, String dosen){

        this.tanggal = tanggal;
        this.nim = nim;
        this.nama = nama;
        this.pertemuan = pertemuan;
        this.tempat = tempat;
        this.dosen = dosen;


    }
}
