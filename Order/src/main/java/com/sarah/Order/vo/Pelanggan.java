package com.sarah.Order.vo;

public class Pelanggan {
    private long id;
    private String kode;
    private String nama;
    private String alamat;

    public Pelanggan() {
    }

    public Pelanggan(long id, String kode, String nama, String alamat) {
        this.id = id;
        this.kode = kode;
        this.nama = nama;
        this.alamat = alamat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
