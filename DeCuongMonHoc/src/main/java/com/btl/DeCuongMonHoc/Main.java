package com.btl.DeCuongMonHoc;

import java.util.function.Consumer;
import java.util.function.Function;

import static com.btl.DeCuongMonHoc.CauHinh.getInt;
import static com.btl.DeCuongMonHoc.CauHinh.sc;
import static com.btl.DeCuongMonHoc.MonDieuKien.MON_HOC_TRUOC;
import static com.btl.DeCuongMonHoc.MonDieuKien.MON_TIEN_QUYET;

public class Main {

    public static void main(String[] args) {
        PhanCong pc = new PhanCong();
        GiangVien gv = loginAccount(pc);

        // thuc hien chuc nang
        int chooseCase;
        do {
            menu();
            chooseCase = getInt();
            switch (chooseCase) {
                case 1 -> {
                    boolean isRepeated = true;
                    do {
                        try {
                            gv.themDeCuong();
                            isRepeated = false;
                            System.out.println("Tạo đề cương thành công");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Môn học đã tồn tại!");
                            System.out.print("""
                                    Bạn có muốn nhập lại?
                                    1. Có
                                    2. Không
                                    Chọn:\s""");
                            int isRetyped = getInt();
                            switch (isRetyped) {
                                case 1 -> System.out.println(
                                        "Vui lòng nhập lại chính xác mã môn học");
                                case 2 -> {
                                    System.out.println("Thoát chức năng tạo đề cương môn học");
                                    isRepeated = false;
                                }
                                default -> announceInvalidValue();
                            }
                        }
                    } while (isRepeated);
                }
                // cap nhat mon tien quyet
                case 2 -> {
                    // kiem tra ma mon hoc co hop le khong
                    MonHoc m = handleInput(gv::timMonHoc, "môn học cần chỉnh sửa thông tin",
                            "cập nhật môn học tiên quyết");

                    // thuc hien cac chuc nang cua case 2
                    if (m != null) {
                        boolean valid = true;
                        do {
                            System.out.print("""
                                    1. Thêm môn học tiên quyết
                                    2. Xóa môn học tiên quyết
                                    3. Thoát
                                    Chọn:\s""");
                            int choiceInCase2 = getInt();
                            switch (choiceInCase2) {
                                case 1 -> {
                                    boolean isRepeated = true;
                                    do {
                                        try {
                                            System.out.println(
                                                    "Nhập thông tin môn học trước cần thêm");
                                            gv.themMonHocDieuKien(m, MON_TIEN_QUYET);
                                            isRepeated = false;
                                        } catch (IllegalArgumentException e) {
                                            System.out.printf(
                                                    "Đề cương của môn học %s không thuộc quản " +
                                                            "lý của giảng viên %s",
                                                    m.getTen(), gv.getTen());
                                            // tra ve true neu muon nhap lai
                                            boolean isRetype = handleRetype(
                                                    "thêm môn học tiên quyết");
                                            if (!isRetype) isRepeated = false;
                                        }
                                    } while (isRepeated);
                                }
                                case 2 -> {
                                    // xuat danh sach mon tien quyet hien tai cua mon hoc can xoa mon
                                    // tien quyet
                                    System.out.println("Danh sách môn học tiên quyết của môn học " +
                                            m.getTen());
                                    m.dsMonTienQuyet().forEach(monHoc -> System.out.printf("""
                                            Mã môn học: %s
                                            Tên môn học: %s
                                            """, monHoc.getMa(), monHoc.getTen()));

                                    // Nhap ma mon hoc tien quyet can xoa
                                    boolean isRepeated = true;
                                    do {
                                        try {
                                            System.out.print("Nhập mã môn học cần xóa: ");
                                            int requiredCourseId = getInt();
                                            gv.xoaMonHocDieuKien(m,
                                                    requiredCourseId, MON_TIEN_QUYET);
                                            isRepeated = false;
                                        } catch (IllegalArgumentException e) {
                                            boolean isRetype = handleRetype(
                                                    "xóa môn học tiên quyết");
                                            if (!isRetype) isRepeated = false;
                                        }
                                    } while (isRepeated);
                                }
                                case 3 -> System.out.println(
                                        "Thoát chức năng chỉnh sửa môn tiên quyết!");
                                default -> {
                                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại!");
                                    valid = false;
                                }
                            }
                        } while (!valid);
                    }
                }
                // lam tuong tu case 2
                case 3 -> {
                    MonHoc m = handleInput(gv::timMonHoc, "môn học cần chỉnh sửa thông tin",
                            "cập nhật môn học trước");

                    // thuc hien cac chuc nang cua case 2
                    if (m != null) {
                        boolean valid = true;
                        do {
                            System.out.print("""
                                    1. Thêm môn học trước
                                    2. Xóa môn học trước
                                    3. Thoát
                                    Chọn:\s""");
                            int choiceInCase3 = getInt();
                            switch (choiceInCase3) {
                                case 1 -> {
                                    boolean isRepeated = true;
                                    do {
                                        try {
                                            System.out.println(
                                                    "Nhập thông tin môn học trước cần thêm");
                                            gv.themMonHocDieuKien(m, MON_HOC_TRUOC);
                                            isRepeated = false;
                                        } catch (IllegalArgumentException e) {
                                            System.out.printf(
                                                    "Đề cương của môn học %s không thuộc quản " +
                                                            "lý của giảng viên %s",
                                                    m.getTen(), gv.getTen());
                                            // tra ve true neu muon nhap lai
                                            boolean isRetype = handleRetype(
                                                    " thêm môn học trước");
                                            if (!isRetype) isRepeated = false;
                                        }
                                    } while (isRepeated);
                                }
                                case 2 -> {
                                    // xuat danh sach mon truoc hien tai cua mon hoc can xoa mon
                                    // truoc
                                    System.out.println("Danh sách môn học trước của môn học " +
                                            m.getTen());
                                    m.dsMonHocTruoc().forEach(monHoc -> System.out.printf("""
                                            Mã môn học: %s
                                            Tên môn học: %s
                                            """, monHoc.getMa(), monHoc.getTen()));

                                    // Nhap ma mon hoc truoc can xoa
                                    boolean isRepeated = true;
                                    do {
                                        try {
                                            System.out.print("Nhập mã môn học cần xóa: ");
                                            int requiredCourseId = getInt();
                                            gv.xoaMonHocDieuKien(m, requiredCourseId,
                                                    MON_HOC_TRUOC);
                                            isRepeated = false;
                                        } catch (IllegalArgumentException e) {
                                            boolean isRetype = handleRetype(
                                                    "xóa môn học trước");
                                            if (!isRetype) isRepeated = false;
                                        }
                                    } while (isRepeated);
                                }
                                case 3 -> System.out.println(
                                        "Thoát chức năng chỉnh sửa môn học trước!");
                                default -> {
                                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại!");
                                    valid = false;
                                }
                            }
                        } while (!valid);
                    }
                }
                case 4 -> {
                    var dc = handleInput(gv::timDeCuong,
                            "môn học cần chỉnh sửa hình thức đánh giá",
                            "cập nhật hình thức đánh giá");

                    // thuc hien cac chuc nang cua case 2
                    if (dc != null) {
                        System.out.print("""
                                1. Thêm hình thức đánh giá
                                2. Xóa hình thức đánh giá
                                3. Thoát
                                Chọn:\s""");
                        int choiceInCase4 = getInt();
                        switch (choiceInCase4) {
                            case 1 -> gv.themDanhGia(dc);
                            case 2 -> gv.xoaDanhGia(dc);
                            case 3 ->
                                    System.out.println("Thoát chức năng chỉnh sửa môn học trước!");
                            default -> announceInvalidValue();
                        }
                    }
                }
                // tim kiem mon hoc theo ma hoac theo ten -> done
                case 5 -> {
                    int choiceInCase5;
                    do {
                        System.out.print("""
                                1. Tìm kiếm theo mã
                                2. Tìm kiếm theo tên
                                0. Thoát
                                Chọn:\s""");
                        choiceInCase5 = getInt();
                        switch (choiceInCase5) {
                            // tim kiem mon hoc theo ma mon hoc
                            case 1 -> {
                                MonHoc monCanTim = handleInput(id ->
                                                QuanLyDeCuong.findCourseOutline(id).getMonHoc(),
                                        "môn học",
                                        "tìm kiếm môn học theo mã");

                                if (monCanTim != null) {
                                    System.out.println("Thông tin môn học cần tìm");
                                    System.out.println(monCanTim);
                                }
                            }

                            // tim kiem mon hoc theo ten -> co the co nhieu mon hoc cung ten
                            case 2 -> {
                                System.out.print("Nhập tên môn học: ");
                                String kw = sc.nextLine();
                                var monHocs = gv.timMonHoc(kw);

                                if (monHocs.isEmpty()) {
                                    System.out.println("Không có môn học chứa từ khóa " + kw);
                                } else {
                                    System.out.println("Thông tin những môn học cần tìm");
                                    monHocs.forEach(System.out::println);
                                }
                            }

                            case 0 -> System.out.println("Thoát chức năng tìm kiếm môn học!");

                            default -> announceInvalidValue();
                        }
                    } while (choiceInCase5 != 0);
                }
                // Sap xep mon hoc theo tin chi giam dan -> done
                case 6 -> {
                    System.out.println("Danh sách môn học theo tín chỉ giảm dần");
                    gv.sapXepMonHoc();
                    gv.danhSachDeCuong().forEach(System.out::println);
                }
                // danh sach de cuong ma giang vien chiu trach nhiem -> done
                case 7 -> {
                    System.out.println("Danh sách đề cương của giảng viên " + gv.getTen() + "\n");
                    gv.danhSachDeCuong().forEach(deCuongMonHoc ->
                            System.out.printf("Mã môn học: %s\nTên môn học: %s\n\n",
                                    deCuongMonHoc.getMonHoc().getMa(),
                                    deCuongMonHoc.getMonHoc().getTen()
                            ));
                }
                case 8 -> handleInputWithoutReturn(QuanLyDeCuong::xuatDeCuong,
                        "đề cương cần xuất", "tìm kiếm theo xuất đề cương");
                case 9 -> { //Thong ke so luong de cuong theo so tin chi
                    System.out.println("Thống kê đề cương của giảng viên " + gv.getTen() + " theo " +
                            "số " +
                            "tín chỉ");
                    gv.thongKeDC();
                }
                case 10 -> {
                    var dsMonLienQuan = handleInput(gv::dsMonLienQuan, "môn học",
                            "tìm kiếm môn học liên quan");

                    dsMonLienQuan.forEach(monHoc -> System.out.printf("""
                            Mã môn học: %s
                            Tên môn học: %s
                                                        
                            """, monHoc.getMa(), monHoc.getTen()));
                }
                case 11 -> {
                    DeCuongMonHoc deCuong = handleInput(gv::timDeCuong,
                            "đề cương cần chỉnh sửa nội dung",
                            "chỉnh sửa nội dung");
                    if (deCuong != null) deCuong.chinhSuaNoiDung();
                }
                case 0 -> {
                    int isRepeated;
                    System.out.println("Kết thúc quản lý đề cương của giảng viên " +
                            gv.getTen());
                    do {
                        System.out.print("""
                                Bạn có muốn tiếp tục
                                1. Có
                                2. Không
                                Chọn:\s""");
                        isRepeated = getInt();
                        switch (isRepeated) {
                            case 1 -> gv = loginAccount(pc);

                            case 2 -> {
                                System.out.println("Chương trình kết thúc");
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
                                
                =========================MENU=========================
                1. Tạo đề cương cho môn học
                2. Cập nhật môn học tiên quyết
                3. Cập nhật môn học trước
                4. Cập nhật hình thức đánh giá
                5. Tìm kiếm môn học
                6. Sắp xếp môn học theo tín chỉ giảm dần
                7. Danh sách đề cương mà giảng viên chịu trách nhiệm
                8. Xuất một đề cương hoàn chỉnh
                9. Thống kê số lượng đề cương theo tín chỉ
                10. Danh sách những môn học liên quan
                11. Chỉnh sửa nội dung đề cương
                0. Thoát
                ======================================================
                                
                Nhập lựa chọn:\s""");
    }

    public static void announceInvalidValue() {
        System.out.println("Lựa chọn không tồn tại!\nNhập lại!");
    }

    public static GiangVien loginAccount(PhanCong pc) {
        GiangVien gv;
        do {
            System.out.print("Nhập mã giảng viên: ");
            gv = pc.timGiangVien(sc.nextLine());
            if (gv == null)
                System.out.println("Mã giảng viên không tồn tại!! Nhập lại");
        } while (gv == null);
        return gv;
    }

    // return true if continue to retype and return false when the retype is done
    public static boolean handleRetype(String tenChucNang) {
        System.out.print("""
                Mã môn học không đúng hoặc không phải đề cương mà giảng viên quản lý
                Bạn có muốn nhập lại?
                1. Có
                2. Không
                Chọn:\s""");
        int choice = getInt();
        switch (choice) {
            case 1 -> System.out.println(
                    "Vui lòng nhập lại chính xác mã môn học");
            case 2 -> {
                System.out.println("Thoát chức năng " + tenChucNang);
                return false;
            }
            default -> announceInvalidValue();
        }
        return true;
    }

    public static <T> T handleInput(Function<Integer, T> function,
                                    String tenMa, String tenChucNang) {
        boolean isRepeated;
        T monHoc = null;
        do {
            try {
                System.out.printf("Nhập mã %s: ", tenMa);
                int id = getInt();
                monHoc = function.apply(id); // loi khi khong ton tai de cuong
                isRepeated = false;
            } catch (IllegalArgumentException e) {
                isRepeated = handleRetype(tenChucNang);
            }
        } while (isRepeated);
        return monHoc;
    }

    public static void handleInputWithoutReturn(Consumer<Integer> function,
                                                String tenMa, String tenChucNang) {
        boolean isRepeated;
        do {
            try {
                System.out.printf("Nhập mã %s: ", tenMa);
                int id = getInt();
                function.accept(id); // neu loi thuc hien khoi lenh catch
                isRepeated = false;
            } catch (IllegalArgumentException e) {
                isRepeated = handleRetype(tenChucNang);
            }
        } while (isRepeated);
    }
}