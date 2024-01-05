package com.btl.DeCuongMonHoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.btl.DeCuongMonHoc.CauHinh.getInt;
import static com.btl.DeCuongMonHoc.CauHinh.sc;

public class DeCuongMonHoc {

    private MonHoc monHoc;

    private HeDaoTao heDaoTao;

    private List<MucTieu> mucTieu = new ArrayList<>();

    private DanhGia danhGia;

    private GiangVien giangVien;

    public DeCuongMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }

    public DeCuongMonHoc(GiangVien giangVien) {
        this.giangVien = giangVien;
        giangVien.themDeCuong(this);
    }

    public DeCuongMonHoc(MonHoc monHoc, GiangVien giangVien) {
        this.monHoc = monHoc;
        this.giangVien = giangVien;
        giangVien.themDeCuong(this);
    }

    public DeCuongMonHoc(MonHoc monHoc, HeDaoTao heDaoTao, List<MucTieu> mucTieu, DanhGia danhGia, GiangVien giangVien) {
        this.monHoc = monHoc;
        this.heDaoTao = heDaoTao;
        this.mucTieu = mucTieu;
        this.danhGia = danhGia;
        this.giangVien = giangVien;
        giangVien.themDeCuong(this);
    }

    //    getter, setter
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

    public List<MucTieu> getMucTieu() {
        return mucTieu;
    }

    public void setMucTieu(List<MucTieu> mucTieu) {
        this.mucTieu = mucTieu;
    }

    public DanhGia getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(DanhGia danhGia) {
        this.danhGia = danhGia;
    }

    public GiangVien getGiangVien() {
        return giangVien;
    }

    public void setGiangVien(GiangVien giangVien) {
        this.giangVien = giangVien;
    }

    //    ------------------------------------------------------------------------------------------

    public void nhapDeCuong() {

        if (this.monHoc == null) {
            MonHoc monHoc = new MonHoc();
            monHoc.nhapMonHoc();
            this.monHoc = monHoc;
        }

        // nhap he dao tao
        int k;
        do {
            System.out.print("""
                    Chọn hệ đào tạo
                    1. Chính quy
                    2. Liên thông
                    Chọn:\s"""); // \s <=> " "
            k = Integer.parseInt(sc.nextLine());
        } while (k < 1 || k > 2);
        this.heDaoTao = HeDaoTao.convertIntToHeDaoTao(k);

        int soLuong;
        System.out.print("Nhập số lượng mục tiêu: ");
        soLuong = getInt();

        for (int i = 0; i < soLuong; i++) {
            MucTieu tmp = new MucTieu();
            tmp.nhapMucTieu();
            this.mucTieu.add(tmp);
        }

        DanhGia danhGia = new DanhGia();
        danhGia.nhapDanhGia();
        this.danhGia = danhGia;
    }

    public void themDanhGia() {
        this.danhGia.themCotDiem();
    }

    public void xoaDanhGia() {
        boolean isRepeated = true;
        do {
            try {
                this.danhGia.xoaCotDiem();
                isRepeated = false;
            } catch (IllegalArgumentException e) {
                System.out.println("Ten cot diem khong dung");
            } catch (MinSizeExceededException e) {
                System.out.println("Xoa cot diem khong thanh cong vi so cot diem toi thieu la ");
                isRepeated = false;
            }
        } while (isRepeated);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); // muc tieu mon hoc
        this.mucTieu.forEach(mucTieu -> sb.append(mucTieu.toString()));
        if (sb.isEmpty()) sb.append("Không");
        return """
                1. THÔNG TIN MÔN HỌC
                %sHệ đào tạo: %s
                2. MỤC TIÊU MÔN HỌC
                %s
                3. ĐÁNH GIÁ
                %s
                4. GIẢNG VIÊN PHỤ TRÁCH
                %s
                """.formatted(monHoc.toString(), heDaoTao.tenHeDaoTao(), sb.toString(), danhGia.toString(),
                this.giangVien.toString());
    }

    // cach thuc so sanh 2 de cuong bang nhau
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeCuongMonHoc that = (DeCuongMonHoc) o;
        return Objects.equals(monHoc, that.monHoc) && heDaoTao == that.heDaoTao;
    }

    // ghi de lai hash code de dam bao rang 2 doi tuong bang nhau co cung ma hash code
    @Override
    public int hashCode() {
        return Objects.hash(monHoc, heDaoTao);
    }
}
