package com.btl.DeCuongMonHoc;

import java.util.List;

public class GiangVien {
    private static int dem = 0;

    private final String maGV;

    private final String ten;

    private final QuanLyDeCuong quanLyDeCuong;

    {
        this.maGV = String.format("GV%03d", ++GiangVien.dem);
        this.quanLyDeCuong = new QuanLyDeCuong();
    }

    public GiangVien(String ten) {
        this.ten = ten;
    }

//    getter, setter

    public String getMaGV() {
        return maGV;
    }

    public String getTen() {
        return ten;
    }

    //    ----------------------------------------------------------------------------------------

    public void themDeCuong(DeCuongMonHoc... deCuongMonHocs)
            throws IllegalArgumentException {
        this.quanLyDeCuong.themDeCuong(deCuongMonHocs);
    }

    public void themDeCuong() throws IllegalArgumentException {
        DeCuongMonHoc deCuongMonHoc = new DeCuongMonHoc(null, this);
        deCuongMonHoc.nhapDeCuong();
        this.themDeCuong(deCuongMonHoc);
    }

    public void themMonHocDieuKien(MonHoc monCanBoSung, MonDieuKien monDieuKien) throws IllegalArgumentException {
        this.quanLyDeCuong.themMonHocDieuKien(monCanBoSung, monDieuKien);
    }

    public void xoaMonHocDieuKien(MonHoc m, int id, MonDieuKien monDieuKien)
            throws IllegalArgumentException {
        this.quanLyDeCuong.xoaMonDieuKien(m, id, monDieuKien);
    }

    public void themDanhGia(DeCuongMonHoc dc) {
        this.quanLyDeCuong.themDanhGia(dc);
    }

    public void xoaDanhGia(DeCuongMonHoc dc) {
        this.quanLyDeCuong.xoaDanhGia(dc);
    }

    public DeCuongMonHoc timDeCuong(int id) throws IllegalArgumentException {
        return this.quanLyDeCuong.timDeCuong(id);
    }

    public MonHoc timMonHoc(int id) throws IllegalArgumentException {
        return this.quanLyDeCuong.timMonHoc(id);
    }

    public List<MonHoc> timMonHoc(String kw) {
        return this.quanLyDeCuong.timMonHoc(kw);
    }

    public List<MonHoc> dsMonLienQuan(int id) throws IllegalArgumentException {
        return this.quanLyDeCuong.dsMonLienQuan(id);
    }

    public void sapXepMonHoc() {
        this.quanLyDeCuong.sapXep();
    }

    public List<DeCuongMonHoc> danhSachDeCuong() {
        return this.quanLyDeCuong.danhSachDeCuong();
    }

    public void thongKeDC() {
        this.quanLyDeCuong.thongKeTheoTinChi();
    }

    @Override
    public String toString() {
        return String.format("%s\n%s\n", this.maGV, this.ten);
    }
}
