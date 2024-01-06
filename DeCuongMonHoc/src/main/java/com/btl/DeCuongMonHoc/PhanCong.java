package com.btl.DeCuongMonHoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Function;

import static com.btl.DeCuongMonHoc.CauHinh.monHocPath;
import static com.btl.DeCuongMonHoc.HeDaoTao.CHINH_QUY;
import static com.btl.DeCuongMonHoc.HeDaoTao.LIEN_THONG;
import static com.btl.DeCuongMonHoc.KhoiKienThuc.*;
import static java.lang.Integer.parseInt;

public class PhanCong {

    private final List<GiangVien> phanCongGV = new ArrayList<>();
    private final QuanLyDeCuong quanLyDeCuong = new QuanLyDeCuong();

    {
        this.docThongTinGiangVien();
        this.docThongTinDeCuong();
        this.phanCongDeCuong();
    }

    public void themGiangVien(GiangVien giangVien) {
        this.phanCongGV.add(giangVien);
    }

    public void themGiangVien() {
        GiangVien giangVien = new GiangVien();
        giangVien.nhapGiangVien();
        this.themGiangVien(giangVien);
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

    private static KhoiKienThuc parseStringToKKT(String s) {
        if (s.equalsIgnoreCase("Cơ sở"))
            return CO_SO;
        else if (s.equalsIgnoreCase("Cơ sở ngành"))
            return CO_SO_NGANH;
        else if (s.equalsIgnoreCase("Chuyên ngành"))
            return CHUYEN_NGANH;
        return null;
    }

    private static HeDaoTao parseStringToHDT(String s) {
        if (s.equalsIgnoreCase("Chính quy"))
            return CHINH_QUY;
        else if (s.equalsIgnoreCase("Liên thông")) return LIEN_THONG;
        return null;
    }

    public Map<GiangVien, List<Integer>> docThongTinGiangVien() {
        File f = new File(CauHinh.giangVienPath);
        try (Scanner scanner = new Scanner(f)) {
            Map<GiangVien, List<Integer>> map = new HashMap<>();
            while (scanner.hasNextLine()) {
                List<Integer> maDeCuong = new ArrayList<>();
                var dataOfGiangVien = scanner.nextLine().split(" \\| ");
                GiangVien gv = new GiangVien(dataOfGiangVien[1]);
                this.themGiangVien(gv);

                for (int i = 2; i < dataOfGiangVien.length; i++)
                    maDeCuong.add(parseInt(dataOfGiangVien[i]));
                map.put(gv, maDeCuong);
            }
            return map;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<DeCuongMonHoc> docThongTinDeCuong() {
        File courseDataFile = new File(monHocPath);
        try (Scanner sc = new Scanner(courseDataFile)) {
            List<DeCuongMonHoc> list = new ArrayList<>();
            while (sc.hasNextLine()) {
                var data = sc.nextLine().split(" \\| ");
                int id = parseInt(data[0]);
                String nameCourse = data[1];
                int soTinChi = parseInt(data[2]);
                String moTa = data[3];
                KhoiKienThuc k = parseStringToKKT(data[4]);
                MonHoc monHoc = new MonHoc(id, nameCourse, soTinChi, moTa, k);
                HeDaoTao h = parseStringToHDT(data[5]);

                List<MucTieu> mucTieuList = getMucTieu(data);

                Function<Integer, DanhGia> getDanhGiaById = (courseId) -> {
                    File fileDanhGia = new File(CauHinh.danhGiaPath);
                    try (Scanner scanner = new Scanner(fileDanhGia)) {
                        DanhGia danhGia = new DanhGia();
                        while (scanner.hasNextLine()) {
                            var dataOfDanhGia = scanner.nextLine().split(" \\| ");
                            if (parseInt(dataOfDanhGia[0]) == courseId) {
                                int soCot = parseInt(dataOfDanhGia[1]);
                                int idxOfDanhGia = 1;

                                for (int i = 0; i < soCot; i++) {
                                    String noiDung = dataOfDanhGia[++idxOfDanhGia];
                                    String pp = dataOfDanhGia[++idxOfDanhGia];
                                    double tt = parseInt(dataOfDanhGia[++idxOfDanhGia]);
                                    danhGia.themCotDiem(new CotDiem(tt, noiDung, pp));
                                }
                            }
                        }
                        return danhGia;
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                };

                DeCuongMonHoc deCuongMonHoc = new DeCuongMonHoc(monHoc, h, mucTieuList,
                        getDanhGiaById.apply(id));
                list.add(deCuongMonHoc);
            }
            return list;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void phanCongDeCuong() {
        var pc = this.docThongTinGiangVien();
        var dsDeCuong = this.docThongTinDeCuong();
        pc.forEach((giangVien, integers) -> pc.get(giangVien)
                .forEach(id -> {
                    DeCuongMonHoc dc = dsDeCuong.stream().filter(deCuongMonHoc -> deCuongMonHoc
                            .getMonHoc().getMa() == id).findFirst().orElse(null);
                    giangVien.themDeCuong(dc);
                })
        );
    }

    private static List<MucTieu> getMucTieu(String[] data) {
        List<MucTieu> mucTieuList = new ArrayList<>();
        int soMucTieum = parseInt(data[6]);
        int idx = 6;
        for (int i = 0; i < soMucTieum; i++) {
            MucTieu mucTieu = new MucTieu(data[++idx]);
            int soChuanDauRa = parseInt(data[++idx]);
            for (int j = 0; j < soChuanDauRa; j++) {
                ChuanDauRa cd = new ChuanDauRa(mucTieu, data[++idx]);
            }
            mucTieuList.add(mucTieu);
        }
        return mucTieuList;
    }
}

