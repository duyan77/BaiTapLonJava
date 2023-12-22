package com.btl.DeCuongMonHoc;

public class MonHoc {

    private int ma;

    private int soTinChi;

    private String moTa;

    private MonHoc[] monHocTruoc;

    private MonHoc[] monTienQuyet;

    private KhoiKienThuc khoiKienThuc;

    public MonHoc(int ma, int soTinChi, String moTa, MonHoc[] monHocTruoc, MonHoc[] monTienQuyet, KhoiKienThuc khoiKienThuc) {
        this.ma = ma;
        this.soTinChi = soTinChi;
        this.moTa = moTa;
        this.monHocTruoc = monHocTruoc;
        this.monTienQuyet = monTienQuyet;
        this.khoiKienThuc = khoiKienThuc;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public MonHoc[] getMonHocTruoc() {
        return monHocTruoc;
    }

    public void setMonHocTruoc(MonHoc[] monHocTruoc) {
        this.monHocTruoc = monHocTruoc;
    }

    public MonHoc[] getMonTienQuyet() {
        return monTienQuyet;
    }

    public void setMonTienQuyet(MonHoc[] monTienQuyet) {
        this.monTienQuyet = monTienQuyet;
    }

    public KhoiKienThuc getKhoiKienThuc() {
        return khoiKienThuc;
    }

    public void setKhoiKienThuc(KhoiKienThuc khoiKienThuc) {
        this.khoiKienThuc = khoiKienThuc;
    }
}
