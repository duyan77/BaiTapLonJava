package com.btl.DeCuongMonHoc;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.btl.DeCuongMonHoc.CauHinh.getInt;
import static com.btl.DeCuongMonHoc.CauHinh.sc;

public class MonHoc {

    private int ma;

    private String ten;

    private int soTinChi;

    private String moTa;

    private List<MonHoc> monHocTruoc = new LimitedList<>(CauHinh.soLuongMonDieuKien);

    private List<MonHoc> monTienQuyet = new LimitedList<>(CauHinh.soLuongMonDieuKien);

    private KhoiKienThuc khoiKienThuc;

    public MonHoc() {
    }

    public MonHoc(int ma, String ten, int soTinChi, String moTa, KhoiKienThuc khoiKienThuc) {
        this.ma = ma;
        this.ten = ten;
        this.soTinChi = soTinChi;
        this.moTa = moTa;
        this.khoiKienThuc = khoiKienThuc;
    }

    public MonHoc(int ma, String ten, int soTinChi, String moTa, List<MonHoc> monHocTruoc, List<MonHoc> monTienQuyet, KhoiKienThuc khoiKienThuc) {
        this.ma = ma;
        this.ten = ten;
        this.soTinChi = soTinChi;
        this.moTa = moTa;
        this.monHocTruoc = monHocTruoc;
        this.monTienQuyet = monTienQuyet;
        this.khoiKienThuc = khoiKienThuc;
    }

    //    getter, setter
    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
    //---------------------------------------------------------------------------------------------

    //    nhập các thông tin chung
    private void nhapThongTinChung() {
        System.out.print("Nhập mã môn học: ");
        this.ma = getInt();

        System.out.print("Nhập tên môn học: ");
        this.ten = sc.nextLine();

        System.out.print("Nhập số tín chỉ: ");
        this.soTinChi = getInt();

        System.out.print("Nhập mô tả cho môn học: ");
        this.moTa = sc.nextLine();

//        khối kiến thức: cơ sở, cơ sở ngành, chuyên ngành
        int k;
        do {
            System.out.print("""
                    Chọn khối kiến thức
                    1.Cơ sở
                    2.Cơ Sở ngành
                    3.Chuyên ngành
                    Chọn:\s""");
            k = getInt();
        } while (k < 1 || k > 3);
        this.khoiKienThuc = KhoiKienThuc.convertIntToKienThuc(k);
    }

    public void themMonDieuKien(MonHoc monHocCanThem, MonDieuKien monDieuKien) {
        monDieuKien.themMonDieuKien(this, monHocCanThem);
    }

    public void xoaMonHocDieuKien(MonHoc monCanXoa, MonDieuKien monDieuKien) {
        monDieuKien.xoaMonDieuKien(this, monCanXoa);
    }

    //    sử dụng cho constructor không tham số
    public void nhapMonHoc() {
        this.nhapThongTinChung();

        // nhập thông tin môn học trước
        int soMon;
        do {
            System.out.print("Nhập số môn học trước: ");
            soMon = getInt();
            if (soMon > 3)
                System.out.println("Số môn học trước tối đa là 3!");
            else if (soMon < 0)
                System.out.println("Lựa chọn không hợp lệ!");
        } while (soMon < 0 || soMon > 3);

        for (int i = 0; i < soMon; i++) {
            MonHoc m = new MonHoc();
            m.nhapMonHoc();
            this.monHocTruoc.add(m);
        }
        // nhập thông tin môn học tiên quyết
    }

    public List<MonHoc> dsMonTienQuyet() {
        return this.monTienQuyet;
    }

    public List<MonHoc> dsMonHocTruoc() {
        return this.monHocTruoc;
    }

    @Override
    public String toString() {
        String tenCacMonHocTruoc;
        tenCacMonHocTruoc = this.monHocTruoc.stream().map(MonHoc::getTen)
                .collect(Collectors.joining(", "));
        if (tenCacMonHocTruoc.isEmpty()) tenCacMonHocTruoc = "Không";

        return """
                Mã môn học: %d
                Tên môn học: %s
                So tin chi: %d
                Môn học trước: %s
                Mô tả: %s
                Khối kiến thức: %s
                """.formatted(this.ma, this.ten, this.soTinChi, tenCacMonHocTruoc, this.moTa,
                this.khoiKienThuc.tenKhoiKienThuc());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MonHoc monHoc)) return false;
        return ma == monHoc.ma && Objects.equals(ten, monHoc.ten);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ma, ten);
    }
}
