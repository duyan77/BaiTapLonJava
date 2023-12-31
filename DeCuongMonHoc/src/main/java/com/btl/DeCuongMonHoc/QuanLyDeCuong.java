package com.btl.DeCuongMonHoc;

import java.util.*;
import java.util.stream.Collectors;

public class QuanLyDeCuong {
    private static final Set<DeCuongMonHoc> DANH_SACH_DE_CUONG = new HashSet<>();
    // set cac de cuong mon hoc cua rieng giao vien
    private Set<DeCuongMonHoc> set = new LimitedSet<>(CauHinh.soLuongDeCuong);

    public void themDeCuong(DeCuongMonHoc... deCuongMonHoc) {
        Arrays.stream(deCuongMonHoc).forEach(dc -> {
            if (this.checkDuplicate(dc))
                throw new IllegalArgumentException("Đề cương cho môn học này đã tồn tại!");
            else
                this.set.add(dc);
            QuanLyDeCuong.DANH_SACH_DE_CUONG.addAll(set);
        });
    }

    // them de cuong bang cach tu nhap
    public void themDeCuong() {
        DeCuongMonHoc tmp = new DeCuongMonHoc();
        tmp.nhapDeCuong();
        this.themDeCuong(tmp);
    }

    private boolean checkDuplicate(DeCuongMonHoc deCuongMonHoc) {
        return QuanLyDeCuong.DANH_SACH_DE_CUONG.contains(deCuongMonHoc);
    }

    // tim kiem mon hoc cua de cuong theo ma mon hoc
    public MonHoc timMonHoc(int id) {
        return QuanLyDeCuong.DANH_SACH_DE_CUONG.stream()
                .map(DeCuongMonHoc::getMonHoc)
                .filter(monHoc -> monHoc.getMa() == id)
                // if a value is present, returns the value otherwise return null
                .findFirst().orElse(null);
    }

    // tim kiem mon hoc cua de cuong theo ten mon hoc
    public List<MonHoc> timMonHoc(String kw) {
        return QuanLyDeCuong.DANH_SACH_DE_CUONG.stream()
                .map(DeCuongMonHoc::getMonHoc)
                .filter(monHoc -> monHoc.getTen().contains(kw))
                .collect(Collectors.toList());
    }

    // tim kiem chinh xac mon hoc
    private static MonHoc findCourseByName(String name) {
        return DANH_SACH_DE_CUONG.stream().map(DeCuongMonHoc::getMonHoc)
                .filter(monHoc -> monHoc.getTen().equalsIgnoreCase(name))
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
        return QuanLyDeCuong.DANH_SACH_DE_CUONG.stream().map(DeCuongMonHoc::getMonHoc)
                .filter(monHoc -> monHoc.dsMonHocTruoc().contains(m) ||
                                  monHoc.dsMonTienQuyet().contains(m))
                .collect(Collectors.toList());
    }

    public List<MonHoc> getRelatedCoures(String nameOfCourse) {
        MonHoc m = QuanLyDeCuong.findCourseByName(nameOfCourse);
        if (m == null) {
            throw new IllegalArgumentException("Ten mon hoc khong dung");
        }
        return QuanLyDeCuong.DANH_SACH_DE_CUONG.stream().map(DeCuongMonHoc::getMonHoc)
                .filter(monHoc -> monHoc.dsMonHocTruoc().contains(m) ||
                                  monHoc.dsMonTienQuyet().contains(m))
                .collect(Collectors.toList());
    }

    // tra ve danh sach cac de cuong
    public List<DeCuongMonHoc> danhSachDeCuong() {
        return this.set.stream().toList();
    }

    public void sapXep() {
        List<DeCuongMonHoc> sortedArray = new ArrayList<>(this.set);
        sortedArray.sort(new DeCuongComparator());

        this.set = new LinkedHashSet<>(sortedArray);
    }

    public void xuatDeCuong(int id) {
        DeCuongMonHoc dc = this.set.stream()
                .filter(deCuongMonHoc -> deCuongMonHoc.getMonHoc().getMa() == id)
                .findFirst().orElse(null);

        System.out.println(dc);
    }

    // thong ke de cuong theo tin chi
    public void thongKeTheoTinChi() {
        // tree map sap xep tang dan theo key
        Map<Integer, List<DeCuongMonHoc>> thongKeDeCuong = new TreeMap<>();

        for (DeCuongMonHoc deCuongMonHoc : set) {
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

