package com.btl.DeCuongMonHoc;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.btl.DeCuongMonHoc.CauHinh.getInt;
import static com.btl.DeCuongMonHoc.CauHinh.sc;
import static com.btl.DeCuongMonHoc.Main.announceInvalidValue;
import static com.btl.DeCuongMonHoc.MonDieuKien.MON_HOC_TRUOC;
import static com.btl.DeCuongMonHoc.MonDieuKien.MON_TIEN_QUYET;

public class MonHoc {

    private int ma;

    private String ten;

    private int soTinChi;

    private String moTa;

    private final List<MonHoc> monHocTruoc = new LimitedList<>(CauHinh.soLuongMonDieuKien);

    private final List<MonHoc> monTienQuyet = new LimitedList<>(CauHinh.soLuongMonDieuKien);

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

    //    getter, setter
    public int getMa() {
        return ma;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public String getTen() {
        return ten;
    }

    //---------------------------------------------------------------------------------------------

    //    nhập các thông tin chung
    private void nhapThongTinChung() throws IllegalArgumentException {
        System.out.print("Nhập mã môn học: ");
        this.ma = getInt();

        DeCuongMonHoc dc = QuanLyDeCuong.findCourseOutline(ma);
        if (dc != null)
            throw new IllegalArgumentException(dc.getMonHoc().getTen());

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

    public void xoaMonHocDieuKien(MonHoc monCanXoa, MonDieuKien monDieuKien) {
        monDieuKien.xoaMonDieuKien(this, monCanXoa);
    }

    //    sử dụng cho constructor không tham số
    public void nhapMonHoc() throws IllegalArgumentException {
        boolean valid = false;
        do {
            try {
                this.nhapThongTinChung();
                valid = true;
            } catch (IllegalArgumentException e) {
                System.out.print("""
                        Môn học này đã tồn tại
                        Bạn có muốn nhập lại?
                        1. Có
                        2. Không
                        Chọn:\s""");
                int choice = getInt();
                switch (choice) {
                    case 1 -> System.out.println("Nhập lại thông tin môn học " + this.ten);
                    case 2 -> throw new IllegalArgumentException(e.getMessage());
                    default -> announceInvalidValue();
                }
            }
        } while (!valid);

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
        themMonDieuKien(soMon, MON_HOC_TRUOC);


        // nhập thông tin môn học tiên quyết
        do {
            System.out.print("Nhập số môn học tiên quyết: ");
            soMon = getInt();
            if (soMon > 3)
                System.err.println("Số môn học tiên quyết tối đa là 3!");
            else if (soMon < 0)
                System.err.println("Lựa chọn không hợp lệ!");
        } while (soMon < 0 || soMon > 3);
        themMonDieuKien(soMon, MON_TIEN_QUYET);
    }

    public void themMonDieuKien(int soMon, MonDieuKien monDieuKien) {
        for (int i = 0; i < soMon; i++) {
            int choose;
            do {
                System.out.print("1.Thêm mới môn học\n2.Thêm môn có sẵn\nBạn chọn: ");
                choose = getInt();
                if (choose == 1) {
                    MonHoc m = new MonHoc();
                    try {
                        m.nhapMonHoc();
                        monDieuKien.themMonDieuKien(this, m);
                    } catch (IllegalArgumentException e) {
                        System.out.printf(
                                "Môn học %s sẽ không được thêm vào danh sách %s của môn %s\n",
                                e.getMessage(), monDieuKien.tenHeDaoTao(), this.getTen());
                        return;
                    }
                } else if (choose == 2) {
                    DeCuongMonHoc m;
                    do {
                        System.out.print("Nhập mã môn học: ");
                        int id = getInt();
                        m = QuanLyDeCuong.findCourseOutline(id);
                        if (m != null) {
                            monDieuKien.themMonDieuKien(this, m.getMonHoc());
                        } else {
                            System.out.println("Môn học chưa có sẵn");
                        }
                    } while (m == null);
                } else {
                    System.out.println("Không hợp lệ!! Nhập lại ");
                }
            } while (choose > 2 || choose < 1);
        }
    }

    public List<MonHoc> dsMonTienQuyet() {
        return this.monTienQuyet;
    }

    public List<MonHoc> dsMonHocTruoc() {
        return this.monHocTruoc;
    }

    @Override
    public String toString() {
        String tenCacMonHocTienQuyet;
        tenCacMonHocTienQuyet = this.monTienQuyet.stream().map(MonHoc::getTen)
                .collect(Collectors.joining(", "));
        if (tenCacMonHocTienQuyet.isEmpty()) tenCacMonHocTienQuyet = "Không";
        String tenCacMonHocTruoc;
        tenCacMonHocTruoc = this.monHocTruoc.stream().map(MonHoc::getTen)
                .collect(Collectors.joining(", "));
        if (tenCacMonHocTruoc.isEmpty()) tenCacMonHocTruoc = "Không";

        return """
                Mã môn học: %d
                Tên môn học: %s
                Số tín chỉ: %d
                Môn học tiên quyết: %s
                Môn học trước: %s
                Mô tả: %s
                Khối kiến thức: %s
                """.formatted(this.ma, this.ten, this.soTinChi, tenCacMonHocTienQuyet, tenCacMonHocTruoc, this.moTa,
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
