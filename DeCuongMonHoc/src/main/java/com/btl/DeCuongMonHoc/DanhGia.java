package com.btl.DeCuongMonHoc;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static com.btl.DeCuongMonHoc.CauHinh.soCotDiemToiDa;
import static com.btl.DeCuongMonHoc.CauHinh.soCotDiemToiThieu;

public class DanhGia {

    private int soCotDiem;

    private final List<CotDiem> cotDiem; // cot diem toi thieu 2 va toi da 4

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
        Arrays.stream(cotDiems).forEach(cotDiem1 -> {
            if (this.cotDiem.size() >= soCotDiemToiDa)
                throw new IllegalArgumentException("Số cột điểm tối thiểu 2 và tối đa 4!");
            else this.cotDiem.add(cotDiem1);
        });
        this.soCotDiem = this.cotDiem.size();
    }

    public void themCotDiem() {
        CotDiem tmp = new CotDiem();
        System.out.println("Nhập cột điểm " + tmp.getTenDiem());
        tmp.nhapCotDiem();
        this.themCotDiem(tmp);
    }

    // xoa hinh thuc danh gia
    public void xoaCotDiem(CotDiem cotDiem) {
        this.cotDiem.remove(cotDiem);
        this.soCotDiem = this.cotDiem.size();
    }

    public void xoaCotDiem(int index) {
        CotDiem cd = this.cotDiem.get(index);
        if (cd == null) {
            throw new IllegalArgumentException("Chi so de xoa diem khong dung!");
        }
        this.xoaCotDiem(cd);
    }

    @Override
    public String toString() {
        return this.cotDiem.stream().map(CotDiem::toString).collect(Collectors.joining());
    }
}
