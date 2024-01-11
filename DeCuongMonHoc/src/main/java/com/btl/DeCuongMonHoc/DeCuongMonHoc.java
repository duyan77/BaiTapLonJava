package com.btl.DeCuongMonHoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.btl.DeCuongMonHoc.CauHinh.SEPARATOR;
import static com.btl.DeCuongMonHoc.CauHinh.getInt;

public class DeCuongMonHoc {

    private MonHoc monHoc;

    private HeDaoTao heDaoTao;

    private List<MucTieu> mucTieu = new ArrayList<>();

    private NoiDungDeCuong noiDungDeCuong;

    private DanhGia danhGia;

    private GiangVien giangVien;

    public DeCuongMonHoc() {

    }

    public DeCuongMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }

    public DeCuongMonHoc(MonHoc monHoc, GiangVien giangVien) {
        this.monHoc = monHoc;
        this.giangVien = giangVien;
    }

    public DeCuongMonHoc(MonHoc monHoc, HeDaoTao heDaoTao, List<MucTieu> mucTieu,
                         NoiDungDeCuong noiDungDeCuong, DanhGia danhGia) {
        this.monHoc = monHoc;
        this.heDaoTao = heDaoTao;
        this.mucTieu = mucTieu;
        this.noiDungDeCuong = noiDungDeCuong;
        this.danhGia = danhGia;
    }

    public DeCuongMonHoc(MonHoc monHoc, HeDaoTao heDaoTao, List<MucTieu> mucTieu, NoiDungDeCuong noiDungDeCuong, DanhGia danhGia, GiangVien giangVien) {
        this.monHoc = monHoc;
        this.heDaoTao = heDaoTao;
        this.mucTieu = mucTieu;
        this.noiDungDeCuong = noiDungDeCuong;
        this.danhGia = danhGia;
        this.giangVien = giangVien;
    }

    //    getter, setter
    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setHeDaoTao(HeDaoTao heDaoTao) {
        this.heDaoTao = heDaoTao;
    }

    public void setGiangVien(GiangVien giangVien) {
        this.giangVien = giangVien;
    }
//    ------------------------------------------------------------------------------------------

    public void nhapDeCuong() {

        if (this.monHoc == null) {
            MonHoc monHoc = new MonHoc();
            monHoc.nhapMonHoc();
            this.monHoc = monHoc;
        }

        // nhap he dao tao
        int k;
        do {
            System.out.print("""
                    Chọn hệ đào tạo
                    1. Chính quy
                    2. Liên thông
                    Chọn:\s"""); // \s <=> " "
            k = getInt();
        } while (k < 1 || k > 2);
        this.heDaoTao = HeDaoTao.convertIntToHeDaoTao(k);

        // nhap muc tieu
        int soLuong;
        System.out.print("Nhập số lượng mục tiêu: ");
        soLuong = getInt();

        for (int i = 0; i < soLuong; i++) {
            MucTieu tmp = new MucTieu();
            tmp.nhapMucTieu();
            this.mucTieu.add(tmp);
        }

        // nhap noi dung de cuong
        NoiDungDeCuong nd = new NoiDungDeCuong();
        System.out.print("Nhập số nội dung môn học: ");
        int soChuong = getInt();
        nd.themNoiDung(soChuong);
        this.noiDungDeCuong = nd;

        // nhap danh gia
        DanhGia danhGia = new DanhGia();
        danhGia.nhapDanhGia();
        this.danhGia = danhGia;
    }

    public void themDanhGia() {
        this.danhGia.themCotDiem();
    }

    public void xoaDanhGia() {
        boolean isRepeated = true;
        do {
            try {
                this.danhGia.xoaCotDiem();
                isRepeated = false;
            } catch (IllegalArgumentException e) {
                System.out.println("Tên cột điểm không đúng");
            } catch (MinSizeExceededException e) {
                System.out.println("Xóa cột điểm không thành công vì số cột điểm tối thiểu là ");
                isRepeated = false;
            }
        } while (isRepeated);
    }

    public void chinhSuaNoiDung() {
        try {
            this.noiDungDeCuong.suaNoiDung();
        } catch (IllegalArgumentException e) {
            System.out.println("Mã đề cương không hợp lệ!");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); // muc tieu mon hoc
        sb.append(SEPARATOR)
                .append(String.format("%-12s%-20s%-80s", "Mục tiêu", "Chuẩn đầu ra", "Mô tả"))
                .append(SEPARATOR);
        this.mucTieu.forEach(mucTieu -> sb.append(mucTieu.toString()));
        sb.append(SEPARATOR);
        if (sb.isEmpty()) sb.append("Không");
        return """
                \n%35s==========ĐỀ CƯƠNG MÔN HỌC===========
                %30s==================**********===================
                                
                1. THÔNG TIN MÔN HỌC
                %sHệ đào tạo: %s
                                
                2. MỤC TIÊU MÔN HỌC
                %s
                3. NỘI DUNG MÔN HỌC
                %s
                4. ĐÁNH GIÁ
                %s
                5. GIẢNG VIÊN BIÊN SOẠN
                %s""".formatted("", "", monHoc.toString(), heDaoTao.tenHeDaoTao(),
                sb.toString(), noiDungDeCuong.toString(),
                danhGia.toString(), giangVien.toString());
    }

    // cach thuc so sanh 2 de cuong bang nhau
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeCuongMonHoc that = (DeCuongMonHoc) o;
        return Objects.equals(monHoc, that.monHoc) && heDaoTao == that.heDaoTao;
    }

    // ghi de lai hash code de dam bao rang 2 doi tuong bang nhau co cung ma hash code
    @Override
    public int hashCode() {
        return Objects.hash(monHoc, heDaoTao);
    }
}
