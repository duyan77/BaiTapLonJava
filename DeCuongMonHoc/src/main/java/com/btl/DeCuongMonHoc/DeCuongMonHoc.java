package com.btl.DeCuongMonHoc;

public class DeCuongMonHoc {

    private MonHoc monHoc;

    private HeDaoTao heDaoTao;

    private MucTieu mucTieu;

    private DanhGia danhGia;

    public DeCuongMonHoc(MonHoc monHoc, HeDaoTao heDaoTao, MucTieu mucTieu, DanhGia danhGia) {
        this.monHoc = monHoc;
        this.heDaoTao = heDaoTao;
        this.mucTieu = mucTieu;
        this.danhGia = danhGia;
    }

    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }

    public HeDaoTao getHeDaoTao() {
        return heDaoTao;
    }

    public void setHeDaoTao(HeDaoTao heDaoTao) {
        this.heDaoTao = heDaoTao;
    }

    public MucTieu getMucTieu() {
        return mucTieu;
    }

    public void setMucTieu(MucTieu mucTieu) {
        this.mucTieu = mucTieu;
    }

    public DanhGia getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(DanhGia danhGia) {
        this.danhGia = danhGia;
    }
}
