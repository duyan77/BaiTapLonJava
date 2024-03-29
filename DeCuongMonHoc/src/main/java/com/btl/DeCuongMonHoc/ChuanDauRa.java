package com.btl.DeCuongMonHoc;

import static com.btl.DeCuongMonHoc.CauHinh.sc;

// lớp này coi như tạm ổn
public class ChuanDauRa {
    private static int dem = 0;
    private final MucTieu mucTieu;
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
        this.mucTieu.themChuanDauRa(this);
    }

    // bổ sung method 1
    public void nhapChuanDauRa() {
        System.out.printf("Nhập mô tả cho chuẩn đầu ra %s: ", this.tenChuanDauRa);
        this.moTa = sc.nextLine();
    }

    // bổ sung method 2
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-20s%-80s\n%12s", this.tenChuanDauRa, this.moTa, ""));
        return sb.toString();
    }
}
