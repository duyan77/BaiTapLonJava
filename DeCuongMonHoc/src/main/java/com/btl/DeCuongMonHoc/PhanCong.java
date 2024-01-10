package com.btl.DeCuongMonHoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.btl.DeCuongMonHoc.CauHinh.monHocPath;
import static com.btl.DeCuongMonHoc.HeDaoTao.CHINH_QUY;
import static com.btl.DeCuongMonHoc.HeDaoTao.LIEN_THONG;
import static com.btl.DeCuongMonHoc.KhoiKienThuc.*;
import static java.lang.Integer.parseInt;

public class PhanCong {

    public final List<GiangVien> phanCongGV = new ArrayList<>();

    {
        this.docThongTinGiangVien();
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
        return this.phanCongGV.stream()
                .filter(giangVien -> giangVien.getMaGV().equalsIgnoreCase(maGV))
                .findFirst().orElse(null);
    }

    // danh sach de cuong cua giang vien
    public List<DeCuongMonHoc> danhSachDeCuong(String id) {
        return this.phanCongGV.stream()
                .filter(giangVien -> giangVien.getMaGV().equals(id))
                .findFirst().orElseThrow().danhSachDeCuong();
    }

    private static KhoiKienThuc convertStrToKKT(String s) {
        if (s.equalsIgnoreCase("Cơ sở"))
            return CO_SO;
        else if (s.equalsIgnoreCase("Cơ sở ngành"))
            return CO_SO_NGANH;
        else if (s.equalsIgnoreCase("Chuyên ngành"))
            return CHUYEN_NGANH;
        return null;
    }

    private static HeDaoTao convertStrToHDT(String s) {
        if (s.equalsIgnoreCase("Chính quy"))
            return CHINH_QUY;
        else if (s.equalsIgnoreCase("Liên thông")) return LIEN_THONG;
        return null;
    }

    public static Map<Integer, String> bangPhanCong() {
        File f = new File(CauHinh.giangVienPath);
        try (Scanner scanner = new Scanner(f)) {
            Map<Integer, String> map = new HashMap<>();
            while (scanner.hasNextLine()) {
                var dataOfGiangVien = scanner.nextLine().split(" \\| ");
                for (int i = 2; i < dataOfGiangVien.length; i++)
                    map.put(parseInt(dataOfGiangVien[i]), dataOfGiangVien[0]);
            }
            return map;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void docThongTinGiangVien() {
        File f = new File(CauHinh.giangVienPath);
        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNextLine()) {
                var dataOfGiangVien = scanner.nextLine().split(" \\| ");
                GiangVien giangVien = new GiangVien(dataOfGiangVien[1]);
                for (int i = 2; i < dataOfGiangVien.length; i++) {
                    int maDeCuong = parseInt(dataOfGiangVien[i]);
                    DeCuongMonHoc dc = docThongTinDeCuong().stream()
                            .filter(deCuongMonHoc -> deCuongMonHoc.getMonHoc()
                                    .getMa() == maDeCuong)
                            .findFirst().orElse(null);
                    if (dc != null)
                        dc.setGiangVien(giangVien);
                    giangVien.themDeCuong(dc);
                }
                this.themGiangVien(giangVien);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static MonHoc getCourseById(int id, List<MonHoc> list) {
        return list.stream().filter(monHoc -> monHoc.getMa() == id)
                .findFirst().orElse(null);
    }

    public static void docThongTinMonHocDieuKien(List<MonHoc> monHocs) {
        File f = new File(CauHinh.monDieuKienPath);
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                var data = sc.nextLine().split(" \\| ");
                MonHoc monHocChinhSua = getCourseById(parseInt(data[0]), monHocs);
                if (monHocChinhSua != null) {
                    // them mon hoc truoc
                    int soMonTruoc = parseInt(data[1]);
                    int idx = 1;
                    if (soMonTruoc == 0) idx++;
                    for (int i = 0; i < soMonTruoc; i++) {
                        MonHoc monHocTruoc = getCourseById(parseInt(data[++idx]), monHocs);
                        monHocChinhSua.dsMonHocTruoc().add(monHocTruoc);
                    }

                    // them mon hoc tien quyet
                    int soMonTienQuyet = parseInt(data[++idx]);
                    for (int i = 0; i < soMonTienQuyet; i++) {
                        MonHoc monHocTruoc = getCourseById(parseInt(data[++idx]), monHocs);
                        monHocChinhSua.dsMonTienQuyet().add(monHocTruoc);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<DeCuongMonHoc> docThongTinDeCuong() {
        File courseDataFile = new File(monHocPath);
        try (Scanner sc = new Scanner(courseDataFile)) {
            List<DeCuongMonHoc> list = new ArrayList<>();
            while (sc.hasNextLine()) {
                var data = sc.nextLine().split(" \\| ");

                // lay thong tin mon hoc
                int id = parseInt(data[0]);
                String nameCourse = data[1];
                int soTinChi = parseInt(data[2]);
                String moTa = data[3];
                KhoiKienThuc k = convertStrToKKT(data[4]);
                MonHoc monHoc = new MonHoc(id, nameCourse, soTinChi, moTa, k);

                // lay thong tin cua de cuong
                HeDaoTao h = convertStrToHDT(data[5]);
                List<MucTieu> mucTieuList = getMucTieu(data);

                // lay thong tin noi dung
                Function<Integer, NoiDungDeCuong> getNoiDungById = (courseId) -> {
                    File fileNoiDung = new File(CauHinh.noiDungPath);
                    try (Scanner scanner = new Scanner(fileNoiDung)) {
                        NoiDungDeCuong noiDungDeCuong = new NoiDungDeCuong();
                        while (scanner.hasNextLine()) {
                            var dataOfND = scanner.nextLine().split(" \\| ");
                            if (parseInt(dataOfND[0]) == courseId) {
                                int soNoiDung = parseInt(dataOfND[1]);
                                int idxOfND = 1;

                                for (int i = 0; i < soNoiDung; i++) {
                                    String nd = dataOfND[++idxOfND];
                                    noiDungDeCuong.themNoiDung(nd);
                                }
                            }
                        }
                        return noiDungDeCuong;
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                };

                // lay thong tin danh gia
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

                list.add(new DeCuongMonHoc(monHoc, h, mucTieuList,
                        getNoiDungById.apply(id), getDanhGiaById.apply(id)));
            }
            // them thong tin mon hoc tien quyet, mon hoc truoc
            var courseList = list.stream().map(DeCuongMonHoc::getMonHoc)
                    .collect(Collectors.toList());
            docThongTinMonHocDieuKien(courseList);

            return list;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private static List<MucTieu> getMucTieu(String[] data) {
        List<MucTieu> mucTieuList = new ArrayList<>();
        int soMucTieum = parseInt(data[6]);
        int idx = 6;
        for (int i = 0; i < soMucTieum; i++) {
            MucTieu mucTieu = new MucTieu(data[++idx]);
            int soChuanDauRa = parseInt(data[++idx]);
            for (int j = 0; j < soChuanDauRa; j++) {
                new ChuanDauRa(mucTieu, data[++idx]);
            }
            mucTieuList.add(mucTieu);
        }
        return mucTieuList;
    }
}

