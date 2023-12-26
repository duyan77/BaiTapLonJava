package com.btl.DeCuongMonHoc;

import java.util.ArrayList;
import java.util.List;

public class PhanCong {

    private final List<GiangVien> phanCongGV = new ArrayList<>();

    // danh sach de cuong cua giang vien
    public List<DeCuongMonHoc> danhSachDeCuong(String id) {
        return this.phanCongGV.stream()
                .filter(giangVien -> giangVien.getMaGV().equals(id))
                .findFirst().orElseThrow().danhSachDeCuong();
    }


}
