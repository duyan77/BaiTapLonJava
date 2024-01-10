package com.btl.DeCuongMonHoc;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static com.btl.DeCuongMonHoc.CauHinh.getInt;
import static com.btl.DeCuongMonHoc.CauHinh.sc;
import static com.btl.DeCuongMonHoc.Main.announceInvalidValue;

public class NoiDungDeCuong {
    private final List<String> noiDung = new LinkedList<>();

    public void themNoiDung(String noiDungThem) {
        this.noiDung.add(noiDungThem);
    }

    public void themNoiDung(int soChuong) {
        for (int i = 0; i < soChuong; i++) {
            int demChuong = this.noiDung.size() + 1;
            System.out.printf("Nhap noi dung chuong thu %d: ", demChuong);
            String noiDungThem = sc.nextLine();
            this.themNoiDung(noiDungThem);
        }
    }

    public void suaNoiDung() throws IllegalArgumentException {
        System.out.println("Noi dung cac chuong");
        System.out.println(this);
        boolean valid = false;
        do {
            try {
                System.out.print("Nhap chuong can sua: ");
                int idx = getInt();
                if (idx < 1 || idx > this.noiDung.size())
                    throw new IndexOutOfBoundsException();
                System.out.printf("Nhap lai noi dung chuong %d: ", idx);
                this.noiDung.set(--idx, sc.nextLine());
                valid = true;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Dau vao khong hop le");
                System.out.print("""
                        Ban co muon nhap lai ?
                        1. Co
                        2. Khong
                        Chon:\s""");
                int choice = getInt();
                switch (choice) {
                    case 1 -> System.out.println("Nhap lai chuong can chinh sua");
                    case 2 -> throw new IllegalArgumentException();
                    default -> announceInvalidValue();
                }
            }
        } while (!valid);
    }

    @Override
    public String toString() {
        if (this.noiDung.isEmpty()) return "Khong";
        
        return this.noiDung.stream()
                .map(nd -> "Chuong %d: %s\n".formatted(this.noiDung.indexOf(nd) + 1, nd))
                .collect(Collectors.joining());
    }
}
