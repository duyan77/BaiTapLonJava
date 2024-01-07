package com.btl.DeCuongMonHoc;

import java.util.Scanner;

public class CauHinh {
    public static final Scanner sc = new Scanner(System.in);
    public static final int soLuongDeCuong = 5;
    public static final int soLuongMonDieuKien = 3;
    public static final int soCotDiemToiThieu = 2;
    public static final int soCotDiemToiDa = 4;
    public static final String monHocPath = "./src/main/resources/MonHoc.txt";
    public static final String monDieuKienPath = "./src/main/resources/MHT_MHTQ.txt";
    public static final String giangVienPath = "./src/main/resources/GiangVien.txt";
    public static final String danhGiaPath = "./src/main/resources/DanhGia.txt";

    public static int getInt() {
        boolean isRepeated = true;
        int returnValue = 0;
        do {
            try {
                returnValue = Integer.parseInt(sc.nextLine());
                isRepeated = false;
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap mot so nguyen");
                System.out.print("Nhap lai: ");
            }
        } while (isRepeated);
        return returnValue;
    }

    public static double getDouble() {
        return Double.parseDouble(sc.nextLine());
    }
}
