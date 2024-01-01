package com.btl.DeCuongMonHoc;

import static com.btl.DeCuongMonHoc.CauHinh.getInt;
import static com.btl.DeCuongMonHoc.CauHinh.sc;

public class Main {

    public static void main(String[] args) {
        PhanCong pc = new PhanCong();
        GiangVien gv;
        pc.themGiangVien();
        gv = loginAccount(pc);

        // thuc hien chuc nang
        int chooseCase;
        do {
            menu();
            chooseCase = getInt();
            switch (chooseCase) {
                case 1 -> {
                    gv.themDeCuong();
                    System.out.println("Tao de cuong thanh cong");
                }
                // cap nhat mon tien quyet
                case 2 -> {
                    // kiem tra ma mon hoc co hop le khong
                    MonHoc m;
                    do {
                        System.out.println("Nhap ma mon hoc can chinh sua thong tin: ");
                        int courseId = getInt();
                        m = gv.timMonHoc(courseId);
                        if (m == null) {
                            System.out.println(
                                    "Ma mon hoc khong dung hoac khong nam trong danh sach " +
                                            "de cuong mon cua giang vien");
                            System.out.println("Vui long nhap lai ma mon hoc!");
                        }
                    } while (m == null);

                    // thuc hien cac chuc nang cua case 2
                    System.out.print("""
                            1. Them mon hoc tien quyet
                            2. Xoa mon hoc tien quyet
                            3. Thoat
                            Chon:\s""");
                    int choiceInCase2 = getInt();
                    switch (choiceInCase2) {
                        case 1 -> {
                            System.out.println("Nhap thong tin mon hoc tien quyet can them");
                            gv.themMonHocTienQuyet(m);
                        }
                        case 2 -> {
                            // xuat danh sach mon tien quyet hien tai cua mon hoc can xoa mon
                            // tien quyet
                            System.out.println("Danh sach mon hoc tien quyet cua mon hoc " +
                                    m.getTen());
                            m.dsMonTienQuyet().forEach(monHoc -> System.out.printf("""
                                    Ma mon hoc: %s
                                    Ten mon hoc: %s
                                    """, monHoc.getMa(), monHoc.getTen()));

                            // Nhap ma mon hoc tien quyet can xoa
                            boolean isRepeated = true;
                            do {
                                System.out.print("Nhap ma mon hoc can xoa: ");
                                int requiredCourseId = getInt();
                                try {
                                    gv.xoaMonHocTienQuyet(m, requiredCourseId);
                                    isRepeated = false;
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Ma mon hoc khong dung! Vui long nhap lai");
                                }
                            } while (isRepeated);
                        }
                        case 0 -> System.out.println("Thoat chuc nang chinh sua mon tien quyet!");
                        default -> announceInvalidValue();
                    }

                }
                // lam tuong tu case 2 -> Ban Nhi lam
                case 3 -> {
                    System.out.println("Nhap ma de cuong");
                }
                case 4 -> {
                    System.out.println("Case 4");
                }
                case 5 -> {
                    int choiceInCase5;
                    do {
                        System.out.print("""
                                1. Tim kiem theo ma
                                2. Tim kiem theo ten
                                0. Thoat
                                Chon:\s""");
                        choiceInCase5 = getInt();
                        switch (choiceInCase5) {
                            // tim kiem mon hoc theo ma mon hoc
                            case 1 -> {
                                try {
                                    System.out.print("Nhap ma mon hoc: ");
                                    int id = getInt();
                                    MonHoc monHoc = gv.timMonHoc(id);
                                    System.out.println("Thong tin mon hoc can tim");
                                    System.out.println(monHoc);
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Ma mon hoc khong ton tai! " +
                                            "Vui long nhap lai");
                                }
                            }

                            // tim kiem mon hoc theo ten -> co the co nhieu mon hoc cung ten
                            case 2 -> {
                                System.out.print("Nhap ten mon hoc: ");
                                String kw = sc.nextLine();
                                var monHocs = gv.timMonHoc(kw);

                                if (monHocs.isEmpty()) {
                                    System.out.println("Khong co mon hoc chua chua ten " + kw);
                                } else {
                                    System.out.println("Thong tin nhung mon hoc can tim");
                                    monHocs.forEach(System.out::println);
                                }
                            }

                            case 0 -> System.out.println("Thoat chuc nang tim kiem mon hoc!");

                            default -> announceInvalidValue();
                        }
                    } while (choiceInCase5 != 0);
                }
                case 6 -> { // Sap xep mon hoc theo tin chi giam dan
                    System.out.println("Danh sach mon hoc theo tin chi giam dan");
                    gv.sapXepMonHoc();
                    gv.danhSachDeCuong().forEach(System.out::println);
                }
                case 7 -> {  //Danh sach de cuong ma giang vien chiu trach nhiem
                    System.out.println("Danh sach de cuong cua giang vien " + gv.getTen());
                    gv.danhSachDeCuong().forEach(deCuongMonHoc ->
                            System.out.printf("Ma mon hoc: %s\nTen Mon Hoc: %s\n",
                                    deCuongMonHoc.getMonHoc().getMa(),
                                    deCuongMonHoc.getMonHoc().getTen()
                            ));
                }
                case 8 -> {
                    try {
                        System.out.print("Nhap ma de cuong can xuat: ");
                        gv.xuatDeCuong(getInt());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ma khong dung vui long nhap lai");
                    }
                }
                case 9 -> { //Thong ke so luong de cuong theo so tin chi
                    System.out.println("Thong ke de cuong cua " + gv.getTen() + " theo so tin chi");
                    gv.thongKeDC();
                }
                case 10 -> {
                    try {
                        System.out.print("Nhap ma mon hoc: ");
                        System.out.println("Danh sach nhung mon hoc lien quan cua mon nay");
                        gv.dsMonLienQuan(getInt()).forEach(monHoc ->
                                System.out.printf("Ma mon hoc: %s\nTen mon hoc: %s\n",
                                        monHoc.getMa(), monHoc.getTen()));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ma mon hoc khong dung hoac khong ton tai trong danh " +
                                "sach de cuong giang vien chiu trach nhiem");
                    }
                }
                case 0 -> {
                    int chooseToContinue;
                    do {
                        System.out.println("Ket thuc quan li de cuong cua giang vien " + gv.getTen());
                        System.out.println("""
                                Ban co muon tiep tuc
                                1.Co
                                2.Khong
                                Chon:\s""");
                        chooseToContinue = getInt();
                        switch (chooseToContinue) {
                            case 1 -> gv = loginAccount(pc);

                            case 2 -> {
                                System.out.println("Chuong trinh ket thuc");
                                return;
                            }

                            default -> announceInvalidValue();
                        }
                    } while (chooseToContinue != 1);
                }
                default -> announceInvalidValue();
            }
        } while (true);
    }

    // method
    public static void menu() {
        System.out.print("""
                1. Tao de cuong cho mon hoc
                2. Cap nhat mon tien quyet
                3. Cap nhap mon hoc truoc
                4. Cap nhat hinh thuc danh gia
                5. Tim kiem mon hoc
                6. Sap xep mon hoc theo tin chi giam dan
                7. Danh sach de cuong ma giang vien chiu trach nhiem
                8. Xuat mot de cuong hoan chinh
                9. Thong ke so luong de cuong theo so tin chi
                10. Danh sach nhung mon hoc lien quan
                0. Thoat
                Nhap lua chon:\s""");
    }

    public static void announceInvalidValue() {
        System.out.println("Lua chon khong ton tai!\nNhap lai!");
    }

    public static GiangVien loginAccount(PhanCong pc) {
        GiangVien gv;
        do {
            System.out.print("Nhap ma giang vien: ");
            gv = pc.timGiangVien(sc.nextLine());
            if (gv == null)
                System.out.println("Mã giảng viên không tồn tại!! Nhập lại");
        } while (gv == null);
        return gv;
    }
}