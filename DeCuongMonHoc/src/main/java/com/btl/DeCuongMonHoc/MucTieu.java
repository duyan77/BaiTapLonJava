package com.btl.DeCuongMonHoc;
// lớp này coi như tạm ổn

import java.util.List;

public class MucTieu {

    private String tenMucTieu;

    private String moTa;

    private List<ChuanDauRa> boChuanDauRa;

    public MucTieu(String mucTieuMon, String moTa) {
        this.tenMucTieu = mucTieuMon;
        this.moTa = moTa;
    }

    // thêm mới chuẩn đầu ra cho mục tiêu
    public void themChuanDauRa(int soChuanDauRa) {
        ChuanDauRa c = new ChuanDauRa(this);
        System.out.println("Chuẩn đầu ra " + c.getTenChuanDauRa());
        System.out.println("Nhập mô tả: "); // nhập mô tả cho chuẩn đầu ra c
        String des = CauHinh.sc.nextLine();
        c.setMoTa(des);
        this.boChuanDauRa.add(c);
    }

    public String getTenMucTieu() {
        return tenMucTieu;
    }

    public void setTenMucTieu(String tenMucTieu) {
        this.tenMucTieu = tenMucTieu;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public List<ChuanDauRa> getBoChuanDauRa() {
        return boChuanDauRa;
    }

    public void setBoChuanDauRa(List<ChuanDauRa> boChuanDauRa) {
        this.boChuanDauRa = boChuanDauRa;
    }
}
