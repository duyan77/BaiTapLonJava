package com.btl.DeCuongMonHoc;

import static com.btl.DeCuongMonHoc.CauHinh.sc;

// lớp này coi như tạm ổn
public class ChuanDauRa {
    private static int dem = 0;
    private MucTieu mucTieu;
    private String tenChuanDauRa; // tên của chuẩn đầu ra

    private String moTa;

    public ChuanDauRa(MucTieu mucTieu, String moTa) {
        this.mucTieu = mucTieu;
        this.moTa = moTa;
        taoTenChuanDauRa();
    }

    public ChuanDauRa(MucTieu mucTieu) {
        this.mucTieu = mucTieu;
        taoTenChuanDauRa();
    }

    //    getter, setter
    public String getTenChuanDauRa() {
        return tenChuanDauRa;
    }

    public void setTenChuanDauRa(String chuanDauRaMon) {
        this.tenChuanDauRa = chuanDauRaMon;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public MucTieu getMucTieu() {
        return mucTieu;
    }

    public void setMucTieu(MucTieu mucTieu) {
        this.mucTieu = mucTieu;
    }

    public static int getDem() {
        return dem;
    }

    public static void setDem(int dem) {
        ChuanDauRa.dem = dem;
    }

    //    ----------------------------------------------------------------------------------------


    private void taoTenChuanDauRa() {
        String tenMucTieu = this.mucTieu.getTenMucTieu();
        String mucTieuThu = tenMucTieu.substring(tenMucTieu.indexOf("O") + 1);
        // CLO <=> course learning outcomes
        this.tenChuanDauRa = "CLO%s.%d".formatted(mucTieuThu, ++dem);
    }

    // bổ sung method 1
    public void nhapChuanDauRa() {
        System.out.printf("Nhập mô tả cho chuẩn đầu ra %s: ", this.tenChuanDauRa);
        this.moTa = sc.nextLine();
    }

    // bổ sung method 2
    @Override
    public String toString() {
        return "%s: %s\n".formatted(this.tenChuanDauRa, this.moTa);
    }
}
