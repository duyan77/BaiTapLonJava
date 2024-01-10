package com.btl.DeCuongMonHoc;
// lớp này coi như tạm ổn

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.btl.DeCuongMonHoc.CauHinh.getInt;
import static com.btl.DeCuongMonHoc.CauHinh.sc;

public class MucTieu {
    private static int dem = 0;

    private String tenMucTieu;

    private String moTa;

    private List<ChuanDauRa> chuanDauRaList = new ArrayList<>();

    {
        this.tenMucTieu = "CO%d".formatted(++dem); // CO <=> course objectives
        ChuanDauRa.setDem(0); // reset biến dem trong class ChuanDauRa mỗi khi tạo mới đối tượng
        // MucTieu
    }

    public MucTieu() {
    }

    public MucTieu(String moTa) {
        this.moTa = moTa;
    }

    //--------------------------------getter, setter---------------------------------------
    public String getTenMucTieu() {
        return tenMucTieu;
    }

    public void setTenMucTieu(String tenMucTieu) {
        this.tenMucTieu = tenMucTieu;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public List<ChuanDauRa> getChuanDauRaList() {
        return chuanDauRaList;
    }

    public void setChuanDauRaList(List<ChuanDauRa> chuanDauRaList) {
        this.chuanDauRaList = chuanDauRaList;
    }
// -------------------------------------------------------------------------------------------------

    // nhập mục tiêu
    public void nhapMucTieu() {
        System.out.println("Mục tiêu " + this.tenMucTieu);
        System.out.printf("Nhập mô tả cho mục tiêu %s: ", this.tenMucTieu);
        this.moTa = sc.nextLine();
        System.out.println("Nhập chuẩn đầu ra cho mục tiêu " + this.tenMucTieu);
        System.out.print("Nhập số chuẩn đầu ra: ");
        int soChuanDauRa = getInt();
        this.themChuanDauRa(soChuanDauRa);
    }

    // thêm mới chuẩn đầu ra cho mục tiêu
    public void themChuanDauRa(int soChuanDauRa) {
        for (int i = 0; i < soChuanDauRa; i++) {
            ChuanDauRa tmp = new ChuanDauRa(this);
            tmp.nhapChuanDauRa();
            this.chuanDauRaList.add(tmp);
        }
    }

    public void themChuanDauRa(ChuanDauRa... chuanDauRas) {
        this.chuanDauRaList.addAll(Arrays.asList(chuanDauRas));
    }

    // xóa chuẩn đẩu ra bằng đối tượng
    public void xoaChuanDauRa(ChuanDauRa chuanDauRa) {
        this.chuanDauRaList.remove(chuanDauRa);
    }

    // xóa chuẩn đầu ra bằng tên
    public void xoaChuaDauRa(String ten) {
        this.chuanDauRaList.removeIf(chuanDauRa -> chuanDauRa.getTenChuanDauRa().equals(ten));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.chuanDauRaList.forEach(chuanDauRa -> sb.append(chuanDauRa.toString()));
        return """
                %-12s%-100s
                """.formatted(this.tenMucTieu, sb.toString());
    }
}
