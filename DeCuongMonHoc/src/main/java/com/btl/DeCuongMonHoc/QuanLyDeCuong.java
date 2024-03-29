package com.btl.DeCuongMonHoc;

import java.util.*;
import java.util.stream.Collectors;

import static com.btl.DeCuongMonHoc.CauHinh.soLuongDeCuong;

public class QuanLyDeCuong {
    private static final Set<DeCuongMonHoc> DANH_SACH_DE_CUONG = new HashSet<>();
    // set cac de cuong mon hoc cua rieng giao vien
    private Set<DeCuongMonHoc> deCuongCuaGV = new LimitedSet<>(soLuongDeCuong);

    public static void themDeCuongTong(DeCuongMonHoc... deCuongMonHocs) {
        QuanLyDeCuong.DANH_SACH_DE_CUONG.addAll(Arrays.asList(deCuongMonHocs));
    }

    public void themDeCuong(DeCuongMonHoc... deCuongMonHoc) {
        Arrays.stream(deCuongMonHoc).forEach(dc -> {
            if (this.checkDuplicate(dc))
                throw new IllegalArgumentException("Đề cương cho môn học này đã tồn tại!");
            else this.deCuongCuaGV.add(dc);
            QuanLyDeCuong.themDeCuongTong(deCuongMonHoc);
        });
    }

    private boolean checkDuplicate(DeCuongMonHoc deCuongMonHoc) {
        return QuanLyDeCuong.DANH_SACH_DE_CUONG.contains(deCuongMonHoc);
    }

    public DeCuongMonHoc timDeCuong(int id) {
        var m = this.deCuongCuaGV.stream()
                .filter(deCuongMonHoc -> deCuongMonHoc.getMonHoc().getMa() == id)
                // if a value is present, returns the value otherwise return null
                .findFirst().orElse(null);
        if (m == null) throw new IllegalArgumentException("Mã môn học không đúng");
        return m;
    }

    // tim kiem mon hoc cua de cuong theo ma mon hoc
    public MonHoc timMonHoc(int id) {
        MonHoc m = this.deCuongCuaGV.stream()
                .map(DeCuongMonHoc::getMonHoc)
                .filter(monHoc -> monHoc.getMa() == id)
                // if a value is present, returns the value otherwise return null
                .findFirst().orElse(null);
        if (m == null)
            throw new IllegalArgumentException(
                    "Mã môn học không đúng hoặc không nằm trong đề cương của giảng viên"
            );
        return m;
    }

    // tim kiem mon hoc cua de cuong theo ten mon hoc
    public List<MonHoc> timMonHoc(String kw) {
        return QuanLyDeCuong.DANH_SACH_DE_CUONG.stream()
                .map(DeCuongMonHoc::getMonHoc)
                .filter(monHoc -> monHoc.getTen().contains(kw))
                .toList();
    }

    public static DeCuongMonHoc findCourseOutline(int id) {
        return DANH_SACH_DE_CUONG.stream().filter(deCuongMonHoc -> deCuongMonHoc.getMonHoc()
                .getMa() == id).findFirst().orElse(null);
    }

    public List<MonHoc> dsMonLienQuan(int id) {
        MonHoc m = QuanLyDeCuong.findCourseOutline(id).getMonHoc();
        if (m == null) {
            throw new IllegalArgumentException("Mã môn học sai");
        }
        System.out.println("Danh sách môn học liên quan của môn " + m.getTen());
        return QuanLyDeCuong.DANH_SACH_DE_CUONG.stream().map(DeCuongMonHoc::getMonHoc)
                .filter(monHoc -> monHoc.dsMonHocTruoc().contains(m) ||
                        monHoc.dsMonTienQuyet().contains(m))
                .collect(Collectors.toList());
    }

    // m la mon hoc muon bo sung them mon dieu kien
    public void themMonHocDieuKien(MonHoc m, MonDieuKien monDieuKien) {
        // danh sach mon hoc cua giao vien
        var courseList = this.deCuongCuaGV.stream()
                .map(DeCuongMonHoc::getMonHoc)
                .toList();
        if (courseList.contains(m)) m.themMonDieuKien(1, monDieuKien);
        else throw new IllegalArgumentException("Mã môn học không đúng");
    }

    public void xoaMonDieuKien(MonHoc m, MonHoc monCanXoa,
                               MonDieuKien monDieuKien) {
        m.xoaMonHocDieuKien(monCanXoa, monDieuKien);
    }

    public void xoaMonDieuKien(MonHoc m, int id, MonDieuKien monDieuKien) {
        // lay mon hoc can xoa
        var requiredCourse = m.dsMonTienQuyet().stream()
                .filter(monHoc -> monHoc.getMa() == id)
                .findFirst().orElse(null);

        if (requiredCourse == null)
            throw new IllegalArgumentException("Mã môn học không đúng");
        this.xoaMonDieuKien(m, requiredCourse, monDieuKien);
    }

    public void themDanhGia(DeCuongMonHoc dc)
            throws MaxSizeExceededException {
        dc.themDanhGia();
    }

    public void xoaDanhGia(DeCuongMonHoc dc) {
        dc.xoaDanhGia();
    }

    // tra ve danh sach cac de cuong
    public List<DeCuongMonHoc> danhSachDeCuong() {
        return this.deCuongCuaGV.stream().toList();
    }

    public void sapXep() {
        List<DeCuongMonHoc> sortedArray = new ArrayList<>(this.deCuongCuaGV);
        sortedArray.sort(new DeCuongComparator());

        this.deCuongCuaGV = new LinkedHashSet<>(sortedArray);
    }

    public static void xuatDeCuong(int id) {
        DeCuongMonHoc dc = QuanLyDeCuong.DANH_SACH_DE_CUONG.stream()
                .filter(deCuongMonHoc -> deCuongMonHoc.getMonHoc().getMa() == id)
                .findFirst().orElse(null);
        if (dc == null) {
            throw new IllegalArgumentException("Mã không đúng");
        }
        System.out.print(dc);
    }

    // thong ke de cuong theo tin chi
    public void thongKeTheoTinChi() {
        // tree map sap xep tang dan theo key
        Map<Integer, List<DeCuongMonHoc>> thongKeDeCuong = new TreeMap<>();

        for (DeCuongMonHoc deCuongMonHoc : deCuongCuaGV) {
            int soTinChi = deCuongMonHoc.getMonHoc().getSoTinChi();
            if (!thongKeDeCuong.containsKey(soTinChi))
                thongKeDeCuong.put(soTinChi, new ArrayList<>());
            thongKeDeCuong.get(soTinChi).add(deCuongMonHoc);
        }

        // ket qua thong ke theo so tin chi tang dan
        for (Map.Entry<Integer, List<DeCuongMonHoc>> entry : thongKeDeCuong.entrySet()) {
            // danh sach mon hoc theo so tin chi
            String courseList = entry.getValue().stream()
                    .map(deCuongMonHoc -> deCuongMonHoc.getMonHoc().getTen())
                    .collect(Collectors.joining(", "));

            System.out.printf("Số tín chỉ: %d - %s\n", entry.getKey(), courseList);
        }
    }

}