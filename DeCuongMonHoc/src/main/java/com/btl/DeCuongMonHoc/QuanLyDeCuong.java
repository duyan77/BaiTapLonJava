package com.btl.DeCuongMonHoc;

import java.util.*;
import java.util.stream.Collectors;

public class QuanLyDeCuong {
    private static final Set<DeCuongMonHoc> DANH_SACH_DE_CUONG = new HashSet<>();
    // set cac de cuong mon hoc cua rieng giao vien
    private Set<DeCuongMonHoc> deCuongCuaGV = new LimitedSet<>(CauHinh.soLuongDeCuong);

    public void themDeCuong(DeCuongMonHoc... deCuongMonHoc) {
        Arrays.stream(deCuongMonHoc).forEach(dc -> {
            if (this.checkDuplicate(dc))
                throw new IllegalArgumentException("Đề cương cho môn học này đã tồn tại!");
            else
                this.deCuongCuaGV.add(dc);
            QuanLyDeCuong.DANH_SACH_DE_CUONG.addAll(deCuongCuaGV);
        });
    }

    private boolean checkDuplicate(DeCuongMonHoc deCuongMonHoc) {
        return QuanLyDeCuong.DANH_SACH_DE_CUONG.contains(deCuongMonHoc);
    }

    private boolean checkContain(DeCuongMonHoc deCuongMonHoc) {
        return this.deCuongCuaGV.contains(deCuongMonHoc);
    }

    // tim kiem mon hoc cua de cuong theo ma mon hoc
    public MonHoc timMonHoc(int id) {
        return this.deCuongCuaGV.stream()
                .map(DeCuongMonHoc::getMonHoc)
                .filter(monHoc -> monHoc.getMa() == id)
                // if a value is present, returns the value otherwise return null
                .findFirst().orElse(null);
    }

    // tim kiem mon hoc cua de cuong theo ten mon hoc
    public List<MonHoc> timMonHoc(String kw) {
        return this.deCuongCuaGV.stream()
                .map(DeCuongMonHoc::getMonHoc)
                .filter(monHoc -> monHoc.getTen().contains(kw))
                .toList();
    }

    // tim kiem chinh xac mon hoc
    public static MonHoc findCourse(String name) {
        return DANH_SACH_DE_CUONG.stream().map(DeCuongMonHoc::getMonHoc)
                .filter(monHoc -> monHoc.getTen().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }

    public static MonHoc findCourse(int id) {
        return DANH_SACH_DE_CUONG.stream().map(DeCuongMonHoc::getMonHoc)
                .filter(monHoc -> monHoc.getMa() == id)
                .findFirst().orElse(null);
    }

    // tim kiem mon hoc truoc hoac mon tien quyet khi biet ma mon hoc
    //    TQ = Tien Quyet
    public List<MonHoc> getRequiredCourese(int id) {
        List<MonHoc> ds = new ArrayList<>();
        MonHoc m = this.timMonHoc(id);

        if (m != null) {
            ds.addAll(m.dsMonHocTruoc()); // them vao danh sach mon hoc truoc
            ds.addAll(m.dsMonTienQuyet()); // them vao danh sach mon hoc tien quyet
        } else throw new IllegalArgumentException("Mã môn học không tồn tại");

        return ds;
    }

    public List<MonHoc> getRelatedCoures(int id) {
        MonHoc m = this.timMonHoc(id);
        if (m == null) {
            throw new IllegalArgumentException("Ma mon hoc sai");
        }
        return QuanLyDeCuong.DANH_SACH_DE_CUONG.stream().map(DeCuongMonHoc::getMonHoc)
                .filter(monHoc -> monHoc.dsMonHocTruoc().contains(m) ||
                        monHoc.dsMonTienQuyet().contains(m))
                .collect(Collectors.toList());
    }

    public List<MonHoc> getRelatedCoures(String nameOfCourse) {
        MonHoc m = QuanLyDeCuong.findCourse(nameOfCourse);
        if (m == null) throw new IllegalArgumentException("Ten mon hoc khong dung");

        return QuanLyDeCuong.DANH_SACH_DE_CUONG.stream().map(DeCuongMonHoc::getMonHoc)
                .filter(monHoc -> monHoc.dsMonHocTruoc().contains(m) ||
                        monHoc.dsMonTienQuyet().contains(m))
                .collect(Collectors.toList());
    }

    public void themMonHocDieuKien(MonHoc m, MonHoc monTienQuyet, MonDieuKien monDieuKien) {
        var courseList = this.deCuongCuaGV.stream()
                .map(DeCuongMonHoc::getMonHoc)
                .toList();
        if (courseList.contains(m)) {
            monDieuKien.themMonDieuKien(m, monTienQuyet);
        } else {
            throw new IllegalArgumentException("Ma mon hoc khong dung");
        }
    }

    public void xoaMonDieuKien(MonHoc m, MonHoc monTienQuyet, MonDieuKien monDieuKien) {
        monDieuKien.xoaMonDieuKien(m, monTienQuyet);
    }

    public void xoaMonDieuKien(MonHoc m, int id, MonDieuKien monDieuKien) {
        var requiredCourse = m.dsMonTienQuyet().stream().filter(monHoc -> monHoc.getMa() == id)
                .findFirst().orElse(null);
        if (requiredCourse == null) {
            throw new IllegalArgumentException("Ma mon hoc khong dung");
        }
        this.xoaMonDieuKien(m, requiredCourse, monDieuKien);
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

    public void xuatDeCuong(int id) {
        DeCuongMonHoc dc = this.deCuongCuaGV.stream()
                .filter(deCuongMonHoc -> deCuongMonHoc.getMonHoc().getMa() == id)
                .findFirst().orElse(null);
        if (dc == null) {
            throw new IllegalArgumentException("Ma khong dung");
        }
        System.out.println(dc);
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
            String courseList = entry.getValue().stream()
                    .map(deCuongMonHoc -> deCuongMonHoc.getMonHoc().getTen())
                    .collect(Collectors.joining(", "));
            System.out.printf("%d - %s\n", entry.getKey(), courseList);
        }
    }
}


