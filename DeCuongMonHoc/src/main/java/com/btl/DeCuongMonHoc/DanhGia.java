package com.btl.DeCuongMonHoc;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.DoubleStream;

import static com.btl.DeCuongMonHoc.CauHinh.soCotDiemToiDa;
import static com.btl.DeCuongMonHoc.CauHinh.soCotDiemToiThieu;

public class DanhGia {

    private int soCotDiem;

    private List<CotDiem> cotDiem; // cot diem toi thieu 2 va toi da 4

    public DanhGia(int soCotDiem) {
        this.soCotDiem = soCotDiem;
        if (soCotDiem > soCotDiemToiDa) {
            throw new IllegalArgumentException("Số cột điểm tối thiểu 2 và tối đa 4!");
        }
        this.cotDiem = new LinkedList<>(); // nen su dung linked list vi chuc nang can thiet chi
        // la them va xoa
    }

    public DanhGia() {
        this(soCotDiemToiThieu);
    }

    public double tongDiem() {
        return this.cotDiem.stream()
                .flatMapToDouble(cotDiem1 -> DoubleStream.of(cotDiem1.getTiTrong()))
                .sum();
    }

    public void themCotDiem(CotDiem... cotDiems) {
        this.cotDiem.addAll(List.of(cotDiems));
    }

    public void themCotDiem() {
        CotDiem tmp = new CotDiem();
        System.out.println("Nhập cột điểm " + tmp.getTenDiem());
        tmp.nhapCotDiem();
        this.themCotDiem(tmp);
    }

    public void xoaCotDiem(CotDiem cotDiem) {
        this.cotDiem.remove(cotDiem);
    }
    
}
