package com.btl.DeCuongMonHoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhanCong {

    private final List<GiangVien> phanCongGV = new ArrayList<>();

    public void themGiangVien(GiangVien... giangVien) {
        this.phanCongGV.addAll(Arrays.asList(giangVien));
    }

    public GiangVien timGiangVien(String maGV) {
        return this.phanCongGV.stream().filter(giangVien -> giangVien.getMaGV().equals(maGV))
                .findFirst().orElse(null);
    }

    // danh sach de cuong cua giang vien
    public List<DeCuongMonHoc> danhSachDeCuong(String id) {
        return this.phanCongGV.stream()
                .filter(giangVien -> giangVien.getMaGV().equals(id))
                .findFirst().orElseThrow().danhSachDeCuong();
    }
}
