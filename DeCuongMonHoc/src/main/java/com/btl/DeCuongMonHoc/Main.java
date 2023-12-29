package com.btl.DeCuongMonHoc;

import static com.btl.DeCuongMonHoc.CauHinh.sc;

public class Main {

    public static void main(String[] args) {
        int choice;
        do {
            menu(); // hien thi menu
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> {
                    System.out.println("Case 1");
                }
                case 2 -> {
                    System.out.println("Case 2");
                }
                case 0 -> {
                    System.out.println("Ket thuc");
                }
                default -> {
                    System.out.println("Nhap lai");
                }
            }
        } while (choice != 0);
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