package com.btl.DeCuongMonHoc;

import java.util.List;

import static com.btl.DeCuongMonHoc.CauHinh.sc;

public class GiangVien {
    private static int dem = 0;

    private String maGV;

    private String ten;

    private final QuanLyDeCuong quanLyDeCuong;

    {
        this.maGV = String.format("GV%03d", ++GiangVien.dem);
        this.quanLyDeCuong = new QuanLyDeCuong();
    }

    public GiangVien() {
    }

    public GiangVien(String ten) {
        this.ten = ten;
    }

//    getter, setter

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    //    ----------------------------------------------------------------------------------------


    public void nhapGiangVien() {
        System.out.print("Nhập tên giảng viên: ");
        this.ten = sc.nextLine();
    }

    public void themDeCuong(DeCuongMonHoc... deCuongMonHocs) {
        this.quanLyDeCuong.themDeCuong(deCuongMonHocs);
    }

    public void themDeCuong() {
        DeCuongMonHoc deCuongMonHoc = new DeCuongMonHoc(this);
        deCuongMonHoc.nhapDeCuong();
        this.themDeCuong(deCuongMonHoc);
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
        return this.quanLyDeCuong.timMonHoc(id);
    }

    public List<MonHoc> timMonHoc(String kw) {
        return this.quanLyDeCuong.timMonHoc(kw);
    }

    public List<MonHoc> dsMonLienQuan(int id) {
        return this.quanLyDeCuong.getRelatedCoures(id);
    }

    public void themMonHocTruoc(int id) {
    }

    public void sapXepMonHoc() {
        this.quanLyDeCuong.sapXep();
    }

    public List<DeCuongMonHoc> danhSachDeCuong() {
        return this.quanLyDeCuong.danhSachDeCuong();
    }

    public void xuatDeCuong(int id) {
        this.quanLyDeCuong.xuatDeCuong(id);
    }

    public void thongKeDC() {
        this.quanLyDeCuong.thongKeTheoTinChi();
    }

    @Override
    public String toString() {
        return this.ten;
    }
}
