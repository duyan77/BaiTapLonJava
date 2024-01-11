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
            System.out.printf("Nhập nội dung chương thứ %d: ", demChuong);
            String noiDungThem = sc.nextLine();
            this.themNoiDung(noiDungThem);
        }
    }

    public void suaNoiDung() throws IllegalArgumentException {
        System.out.println("Nội dung các chương");
        System.out.println(this);
        boolean valid = false;
        do {
            try {
                System.out.print("Nhập nội dung cần sửa: ");
                int idx = getInt();
                if (idx < 1 || idx > this.noiDung.size())
                    throw new IndexOutOfBoundsException();
                System.out.printf("Nhập lại nội dung chương %d: ", idx);
                this.noiDung.set(--idx, sc.nextLine());
                valid = true;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Đầu vào không hợp lệ!");
                System.out.print("""
                        Bạn có muốn nhập lại ?
                        1. Có
                        2. Không
                        Chọn:\s""");
                int choice = getInt();
                switch (choice) {
                    case 1 -> System.out.println("Nhập lại chương cần chỉnh sửa");
                    case 2 -> throw new IllegalArgumentException();
                    default -> announceInvalidValue();
                }
            }
        } while (!valid);
    }

    @Override
    public String toString() {
        if (this.noiDung.isEmpty()) return "Không";
        
        return this.noiDung.stream()
                .map(nd -> "Chương %d: %s\n".formatted(this.noiDung.indexOf(nd) + 1, nd))
                .collect(Collectors.joining());
    }
}
