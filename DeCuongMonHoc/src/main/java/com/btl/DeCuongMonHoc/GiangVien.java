package com.btl.DeCuongMonHoc;

import java.util.List;

public class GiangVien {

    private int maGV;

    private String ten;

    private List<DeCuongMonHoc> dc;

    public GiangVien(int maGV, String ten, List<DeCuongMonHoc> dc) {
        this.maGV = maGV;
        this.ten = ten;
        this.dc = dc;
    }

    public GiangVien(int maGV, String ten) {
        this.maGV = maGV;
        this.ten = ten;
    }

    public void taoDeCuong() {

    }

    public void themDanhGia(DeCuongMonHoc dc) {

    }

    public void themDanhGia(String tenMonHoc) {

    }

    public void xoaDanhGia(DeCuongMonHoc dc, String mucDanhGia) {

    }

    public void suaNoiDung(String tenMonHoc) {

    }

    public void suaNoiDung(DeCuongMonHoc dc) {

    }

    public MonHoc timMonHoc(int id) {
        return null;
    }

    public MonHoc timMonHoc(int id, HeDaoTao he) {
        return null;
    }

    public List<MonHoc> timMonHoc(String kw) {
        return null;
    }

    public List<MonHoc> monTruocVaTQ(int id) {
        return null;
    }

    public List<MonHoc> monTruocVaTQ(int id, HeDaoTao he) {
        return null;
    }

    public void sapXepMonHoc() {

    }

    public List<DeCuongMonHoc> danhSachDeCuong() {
        return null;
    }

    public void xuatDeCuong(DeCuongMonHoc dc) {

    }

    public void thongKeDC() {

    }

}
