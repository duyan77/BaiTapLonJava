package com.btl.DeCuongMonHoc;

import java.util.Arrays;

import static com.btl.DeCuongMonHoc.CauHinh.sc;

public class MonHoc {

    private int ma;

    private String ten;

    private int soTinChi;

    private String moTa;

    private MonHoc[] monHocTruoc;

    private MonHoc[] monTienQuyet;

    private KhoiKienThuc khoiKienThuc;

    public MonHoc() {
    }

    public MonHoc(int ma, String ten, int soTinChi, String moTa, MonHoc[] monHocTruoc, MonHoc[] monTienQuyet, KhoiKienThuc khoiKienThuc) {
        this.ma = ma;
        this.ten = ten;
        this.soTinChi = soTinChi;
        this.moTa = moTa;
        this.monHocTruoc = monHocTruoc;
        this.monTienQuyet = monTienQuyet;
        this.khoiKienThuc = khoiKienThuc;
    }

    public MonHoc(int ma, String ten, int soTinChi, MonHoc[] monHocTruoc, MonHoc[] monTienQuyet, KhoiKienThuc khoiKienThuc) {
        this.ma = ma;
        this.ten = ten;
        this.soTinChi = soTinChi;
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

    public MonHoc[] getMonHocTruoc() {
        return monHocTruoc;
    }

    public void setMonHocTruoc(MonHoc[] monHocTruoc) {
        this.monHocTruoc = monHocTruoc;
    }

    public MonHoc[] getMonTienQuyet() {
        return monTienQuyet;
    }

    public void setMonTienQuyet(MonHoc[] monTienQuyet) {
        this.monTienQuyet = monTienQuyet;
    }

//    public KhoiKienThuc getKhoiKienThuc() {
//        return khoiKienThuc;
//    }

    public void setKhoiKienThuc(KhoiKienThuc khoiKienThuc) {
        this.khoiKienThuc = khoiKienThuc;
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
        this.ma = Integer.parseInt(sc.nextLine());

        System.out.print("Nhập tên môn học: ");
        this.ten = sc.nextLine();

        System.out.print("Nhập số tín chỉ: ");
        this.soTinChi = Integer.parseInt(sc.nextLine());

        System.out.print("Nhập môn tả cho môn học: ");
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
            k = Integer.parseInt(sc.next());
        } while (k < 1 || k > 3);
        this.khoiKienThuc = KhoiKienThuc.convertIntToKienThuc(k);
    }

    //    sử dụng cho constructor không tham số
    public void nhapMonHoc() {
        this.nhapThongTinChung();

        // nhập thông tin môn học trước
        int soMon;
        do {
            System.out.println("Nhập số môn học trước: ");
            soMon = Integer.parseInt(sc.nextLine());
            if (soMon > 3)
                System.err.println("Số môn học trước tối đa là 3!");
            else if (soMon < 0)
                System.err.println("Lựa chọn không hợp lệ!");
        } while (soMon < 0 || soMon > 3);

        this.monHocTruoc = new MonHoc[soMon];
        Arrays.stream(this.monHocTruoc).forEach(MonHoc::nhapThongTinChung);

        // nhập thông tin môn học tiên quyết
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(monHocTruoc).forEach(monHoc -> sb.append(monHoc.ten).append(" "));
        String mHTStr = sb.toString().trim();
        sb.delete(0, sb.length());

        return """
                Mã môn học: %d
                Tên môn học: %s
                Môn học trước: %s
                Mô tả: %s
                Khối kiến thức: %s
                """.formatted(this.ma, this.ten, mHTStr, this.moTa, this.khoiKienThuc.toString());
    }
}
