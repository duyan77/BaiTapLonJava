package com.btl.DeCuongMonHoc;

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

    private void taoTenChuanDauRa() {
        String tenMucTieu = this.mucTieu.getTenMucTieu();
        String mucTieuThu = tenMucTieu.substring(tenMucTieu.indexOf("O") + 1);
        this.tenChuanDauRa = "CLO%s.%d".formatted(mucTieuThu, ++dem);
    }
}
