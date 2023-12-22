package com.btl.DeCuongMonHoc;

public abstract class DiemThanhPhan {

    private String tenThanhPhan;

    private double tiTrong;

    private String noiDung;

    private String phuongPhap;

    public DiemThanhPhan(String ten, double tt) {

    }

    public abstract void noiDungDanhGia();

    public abstract void phuongPhapDanhGia();

}
