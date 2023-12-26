package com.btl.DeCuongMonHoc;

import java.util.*;
import java.util.stream.Collectors;

public class QuanLyDeCuong {
    Set<DeCuongMonHoc> set = new HashSet<>(); // set cac de cuong mon hoc

    public void themDeCuong(DeCuongMonHoc deCuongMonHoc) {
        if (this.checkDuplicate(deCuongMonHoc))
            System.err.println("De cuong cho mon hoc nay da ton tai !!!");
        else
            this.set.add(deCuongMonHoc);
    }

    // them de cuong bang cach tu nhap
    public void themDeCuong() {
        DeCuongMonHoc tmp = new DeCuongMonHoc();
        tmp.nhapDeCuong();
        if (this.checkDuplicate(tmp))
            System.err.println("De cuong cho mon hoc nay da ton tai !!!");
        else
            this.set.add(tmp);
    }

    private boolean checkDuplicate(DeCuongMonHoc deCuongMonHoc) {
        return this.set.contains(deCuongMonHoc);
    }

    // tim kiem mon hoc cua de cuong theo ma mon hoc
    public MonHoc timMonHoc(int id) {
        return this.set.stream()
                .map(DeCuongMonHoc::getMonHoc)
                .filter(monHoc -> monHoc.getMa() == id)
                // if a value is present, returns the value otherwise return null
                .findFirst().orElse(null);
    }

    // tim kiem mon hoc cua de cuong theo ten mon hoc
    public List<MonHoc> timMonHoc(String kw) {
        return this.set.stream()
                .map(DeCuongMonHoc::getMonHoc)
                .filter(monHoc -> monHoc.getTen().contains(kw))
                .collect(Collectors.toList());
    }

    // tim kiem mon hoc truoc hoac mon tien quyet khi biet ma mon hoc
    //    TQ = Tien Quyet
    public List<MonHoc> dsMonTruocVaTQ(int id) {
        List<MonHoc> ds = new ArrayList<>();
        MonHoc m = this.timMonHoc(id);
        
        if (m != null) {
            ds.addAll(m.dsMonHocTruoc()); // them vao danh sach mon hoc truoc
            ds.addAll(m.dsMonTienQuyet()); // them vao danh sach mon hoc tien quyet
        } else System.err.println("Ma mon hoc khong ton tai!");

        return ds;
    }

    // tra ve danh sach cac de cuong
    public List<DeCuongMonHoc> danhSachDeCuong() {
        return this.set.stream().toList();
    }

    public void sapXep() {
        List<DeCuongMonHoc> sortedArray = new ArrayList<>(this.set);
        sortedArray.sort((o1, o2) -> {
            int tc1 = o1.getMonHoc().getSoTinChi();
            int tc2 = o2.getMonHoc().getSoTinChi();

            if (tc1 == tc2)
                return o1.getMonHoc().getMa() - o2.getMonHoc().getMa();
            return tc2 - tc1; // sap xep theo tin chi giam dan
        });

        this.set = new LinkedHashSet<>(sortedArray);
    }

    public void thongKeTheoTinChi() {
        // thong ke de cuong theo tin chi
        Map<Integer, List<DeCuongMonHoc>> thongKeDeCuong = new HashMap<>();
        for (DeCuongMonHoc deCuongMonHoc : set) {
            int soTinChi = deCuongMonHoc.getMonHoc().getSoTinChi();
            if (!thongKeDeCuong.containsKey(soTinChi))
                thongKeDeCuong.put(soTinChi, new ArrayList<>());
            thongKeDeCuong.get(soTinChi).add(deCuongMonHoc);
        }

        // ket qua thong ke
        for (Integer soTinChi : thongKeDeCuong.keySet()) {
            StringBuilder sb = new StringBuilder();
            thongKeDeCuong
                    .get(soTinChi)
                    .forEach(deCuongMonHoc -> sb
                            .append(deCuongMonHoc.getMonHoc().toString())
                            .append(", ")
                    );
            // danh sach cac mon hoc dang chuoi
            String courseList = sb.delete(sb.lastIndexOf(","), sb.length() - 1).toString();

            System.out.printf("%d - %s\n", soTinChi, courseList);
        }
    }
}

