package com.btl.DeCuongMonHoc;

import java.util.function.Consumer;
import java.util.function.Function;

import static com.btl.DeCuongMonHoc.CauHinh.getInt;
import static com.btl.DeCuongMonHoc.CauHinh.sc;
import static com.btl.DeCuongMonHoc.MonDieuKien.MON_TIEN_QUYET;

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
                            gv.themMonHocDieuKien(m, MON_TIEN_QUYET);
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
                // tim kiem mon hoc theo ma hoac theo ten -> done
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
                                MonHoc monCanTim = handleInput(gv::timMonHoc,
                                        "mon hoc",
                                        "tim kiem mon hoc theo ma");

                                if (monCanTim != null) {
                                    System.out.println("Thong tin mon hoc can tim");
                                    System.out.println(monCanTim);
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
                // Sap xep mon hoc theo tin chi giam dan -> done
                case 6 -> {
                    System.out.println("Danh sach mon hoc theo tin chi giam dan");
                    gv.sapXepMonHoc();
                    gv.danhSachDeCuong().forEach(System.out::println);
                }
                // danh sach de cuong ma giang vien chiu trach nhiem -> done
                case 7 -> {
                    System.out.println("Danh sach de cuong cua giang vien " + gv.getTen());
                    gv.danhSachDeCuong().forEach(deCuongMonHoc ->
                            System.out.printf("Ma mon hoc: %s\nTen Mon Hoc: %s\n",
                                    deCuongMonHoc.getMonHoc().getMa(),
                                    deCuongMonHoc.getMonHoc().getTen()
                            ));
                }
                case 8 -> handleInputWithoutReturn(gv::xuatDeCuong, "de cuong can xuat",
                        "tim kiem theo xuat de cuong");
                case 9 -> { //Thong ke so luong de cuong theo so tin chi
                    System.out.println("Thong ke de cuong cua " + gv.getTen() + " theo so tin chi");
                    gv.thongKeDC();
                }
                case 10 -> {
                    var dsMonLienQuan = handleInput(gv::dsMonLienQuan, "mon hoc",
                            "tim kiem mon hoc lien quan");
                    dsMonLienQuan.forEach(monHoc ->
                            System.out.printf("Ma mon hoc: %s\nTen mon hoc: %s\n",
                                    monHoc.getMa(), monHoc.getTen()));
                }
                case 0 -> {
                    int isRepeated;
                    do {
                        System.out.println("Ket thuc quan li de cuong cua giang vien " +
                                gv.getTen());

                        System.out.println("""
                                Ban co muon tiep tuc
                                1.Co
                                2.Khong
                                Chon:\s""");
                        isRepeated = getInt();
                        switch (isRepeated) {
                            case 1 -> gv = loginAccount(pc);

                            case 2 -> {
                                System.out.println("Chuong trinh ket thuc");
                                return;
                            }

                            default -> announceInvalidValue();
                        }
                    } while (isRepeated != 1);
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

    public static boolean handleRetype(int choice, String tenChucNang) {
        System.out.println("""
                Ma mon hoc khong dung!
                Ban co muon nhap lai?
                1. Co
                2. Khong
                Chon:\s""");
        switch (choice) {
            case 1 -> System.out.println(
                    "Vui long nhap lai chinh xac ma mon hoc");
            case 2 -> {
                System.out.println("Thoat chuc nang " + tenChucNang);
                return false;
            }
            default -> announceInvalidValue();
        }
        return true;
    }

    public static <T> T handleInput(Function<Integer, T> function,
                                    String tenMa,
                                    String tenChucNang) {
        boolean isRepeated;
        T monHoc = null;
        do {
            try {
                System.out.printf("Nhap ma %s: ", tenMa);
                int id = getInt();
                monHoc = function.apply(id); // loi khi khong ton tai de cuong
                isRepeated = false;
            } catch (IllegalArgumentException e) {
                isRepeated = handleRetype(getInt(), tenChucNang);
            }
        } while (isRepeated);
        return monHoc;
    }

    public static void handleInputWithoutReturn(Consumer<Integer> function,
                                                String tenMa,
                                                String tenChucNang) {
        boolean isRepeated;
        do {
            try {
                System.out.printf("Nhap ma %s: ", tenMa);
                int id = getInt();
                function.accept(id); // neu loi thuc hien khoi lenh catch
                isRepeated = false;
            } catch (IllegalArgumentException e) {
                isRepeated = handleRetype(getInt(), tenChucNang);
            }
        } while (isRepeated);
    }
}

