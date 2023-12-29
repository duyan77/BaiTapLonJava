package com.btl.DeCuongMonHoc;

import static com.btl.DeCuongMonHoc.CauHinh.*;

public class Main {
    public static void main(String[] args) {
        int choose;
        PhanCong pc = new PhanCong();
        GiangVien gv;
        pc.themGiangVien();
        do {
            System.out.print("Nhap ma giang vien: ");
            gv = pc.timGiangVien(sc.nextLine());
            if(gv == null)
                System.out.println("Mã giảng viên không tồn tại!! Nhập lại ");
        } while (gv == null);
        do {
            menu();
            choose=Integer.parseInt(sc.nextLine());
            switch (choose){
                case 1 -> {
                    MonHoc monHoc = new MonHoc();
                    DeCuongMonHoc dc = new DeCuongMonHoc();
                    dc.nhapDeCuong();
                    dc.setMonHoc(monHoc);
                    System.out.println(dc.toString());
                }
                case 2 -> {

                }
                case 3 -> {

                }
                case 4 -> {

                }
                case 5 -> {

                }
                case 6 -> {

                }
                case 7 -> {

                }
                default -> System.out.println("Lua chon khong ton tai!! Nhap lai.");
                case 0 -> {

                }
            }
        }while (choose!=0);
    }
    private static void menu() {
        System.out.print("""
                1. Tao de cuong cho mon hoc
                2. Cap nhat mon tien quyet
                3. Cap nhap mon hoc truoc
                4. Cap nhat hinh thuc danh gia
                5. Tim kiem mon hoc
                6. Sap xep mon hoc theo tin chi giam dan
                7. Danh sach de cuong ma giang vien chiu trach nhiem
                0. Thoat
                Nhap lua chon:\s""");
    }
}