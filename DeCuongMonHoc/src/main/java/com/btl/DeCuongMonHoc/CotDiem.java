package com.btl.DeCuongMonHoc;

import static com.btl.DeCuongMonHoc.CauHinh.*;

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

    public CotDiem(double tiTrong) {
        this.tiTrong = tiTrong;
    }

    public CotDiem(double tiTrong,
                   String noiDung, String phuongPhap) {
        this.tiTrong = tiTrong;
        this.noiDung = noiDung;
        this.phuongPhap = phuongPhap;
    }

    public static int getDem() {
        return dem;
    }

    public static void setDem(int dem) {
        CotDiem.dem = dem;
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

    public void nhapCotDiem() throws IllegalArgumentException {
        System.out.println("Nhập cột điểm " + this.getTenDiem());
        System.out.print("Nhập nội dung đánh giá: ");
        this.noiDung = sc.nextLine();
        System.out.print("Nhập phương pháp đánh giá: ");
        this.phuongPhap = sc.nextLine();
        System.out.print("Nhập tỉ trọng điểm: ");
        double tt = getDouble();
        if (tt < 0 || tt > 100)
            throw new IllegalArgumentException("Tỉ trọng điểm phải lớn hơn 0 và nhỏ hơn 100");
        this.tiTrong = tt;
    }

    @Override
    public String toString() {
        return SEPARATOR +
               String.format("|%-12s|%-45s|%-35s|%-10s|\n", this.tenDiem, this.noiDung, this.phuongPhap, this.tiTrong);
    }
        //return "Nội dung cột điểm %s\nNội dung: %s\nPhương pháp: %s\nTỉ trọng điểm: %.2f\n"
                //.formatted(this.tenDiem, this.noiDung, this.phuongPhap, this.tiTrong);
}
