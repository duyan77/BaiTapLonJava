package com.btl.DeCuongMonHoc;

import static com.btl.DeCuongMonHoc.CauHinh.sc;

public class CotDiem {

    private static int dem = 0;

    private String tenDiem;

    private double tiTrong;

    private String noiDung;

    private String phuongPhap;

    {
        this.tenDiem = "A%02d".formatted(++CotDiem.dem);
    }

    public CotDiem() {
    }

    public CotDiem(String tenThanhPhan, double tiTrong) {
        this.tenDiem = tenThanhPhan;
        this.tiTrong = tiTrong;
    }

    public CotDiem(String tenThanhPhan, double tiTrong, String noiDung, String phuongPhap) {
        this.tenDiem = tenThanhPhan;
        this.tiTrong = tiTrong;
        this.noiDung = noiDung;
        this.phuongPhap = phuongPhap;
    }

    public String getTenDiem() {
        return tenDiem;
    }

    public void setTenDiem(String tenDiem) {
        this.tenDiem = tenDiem;
    }

    public double getTiTrong() {
        return tiTrong;
    }

    public void setTiTrong(double tiTrong) {
        this.tiTrong = tiTrong;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getPhuongPhap() {
        return phuongPhap;
    }

    public void setPhuongPhap(String phuongPhap) {
        this.phuongPhap = phuongPhap;
    }

    public void nhapCotDiem() {
        System.out.print("Nhap noi dung danh gia: ");
        this.noiDung = sc.nextLine();
        System.out.print("Nhap phuong phap danh gia: ");
        this.phuongPhap = sc.nextLine();
        System.out.print("Nhap ti trong diem: ");
        double tt = Integer.parseInt(sc.nextLine());
        if (tt < 0 || tt > 100)
            throw new IllegalArgumentException("Ti trong diem phai lon hon 0 va nho hon 100");
        this.tiTrong = tt;
    }
}
