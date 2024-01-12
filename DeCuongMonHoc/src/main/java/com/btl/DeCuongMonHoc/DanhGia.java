package com.btl.DeCuongMonHoc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static com.btl.DeCuongMonHoc.CauHinh.*;

public class DanhGia {

    private List<CotDiem> cotDiem = new LimitedList<>(soCotDiemToiThieu, soCotDiemToiDa); // cot diem toi thieu
    // 2 va toi da 4

    {
        CotDiem.setDem(this.cotDiem.size());
    }

    public DanhGia() {
    }

    public DanhGia(List<CotDiem> cotDiem) {
        this.cotDiem = cotDiem;
    }

    //-------------------------------------------------------------------------------------------

    public int soCotDiemHienTai() {
        return this.cotDiem.size();
    }

    public void nhapDanhGia() {
        this.handleWeightInput();

        while (this.tongDiem() != 100) {
            System.out.println("Tổng tỉ trọng phải là 100!");
            System.out.println("Vui lòng nhập lại tỉ trọng phù hợp với các cột điểm");
            this.retypeOldWeight();
        }
    }

    private void handleWeightInput() {
        int soCot;
        do {
            System.out.print("Nhập số cột điểm muốn thêm mới: ");
            soCot = getInt();
            if (soCot + this.soCotDiemHienTai() < soCotDiemToiThieu ||
                    soCot + this.soCotDiemHienTai() > soCotDiemToiDa)
                System.out.printf("""
                        Số cột điểm tối thiểu %d và tối đa %d!
                        Vui lòng nhập lại
                        Số cột điểm hiện tại là: %d
                        """, soCotDiemToiThieu, soCotDiemToiDa, this.soCotDiemHienTai());
        } while (soCot + this.soCotDiemHienTai() < soCotDiemToiThieu ||
                soCot + this.soCotDiemHienTai() > soCotDiemToiDa);

        for (int i = 0; i < soCot; i++) {
            boolean isRepeated = true;
            CotDiem cotDiem = new CotDiem();
            do {
                try {
                    cotDiem.nhapCotDiem();
                    isRepeated = false;
                    this.themCotDiem(cotDiem);
                } catch (IllegalArgumentException e) {
                    System.out.println("Tỉ trọng điểm phải lớn hơn 0 và nhỏ hơn 100");
                    System.out.println("Vui lòng nhập lại giá trị phù hợp!");
                }
            } while (isRepeated);
        }
    }

    private void retypeOldWeight() {
        this.cotDiem.forEach(cotDiem1 -> {
            System.out.printf("""
                            Nội dung cột điểm %s
                            Nội dung: %s
                            Phương pháp: %s
                            Tỉ trọng điểm:\s""",
                    cotDiem1.getTenDiem(), cotDiem1.getNoiDung(),
                    cotDiem1.getPhuongPhap());
            cotDiem1.setTiTrong(getDouble());
        });
    }

    public double tongDiem() {
        return this.cotDiem.stream()
                .flatMapToDouble(cotDiem1 -> DoubleStream.of(cotDiem1.getTiTrong()))
                .sum();
    }

    public void themCotDiem(CotDiem cotDiemThem)
            throws MaxSizeExceededException, IllegalArgumentException {
        this.cotDiem.add(cotDiemThem);
        CotDiem.setDem(this.cotDiem.size());
    }

    public void themCotDiem() {
        System.out.println("Vui lòng nhập lại tỉ trọng của các cột điểm trước");
        this.retypeOldWeight();
        this.nhapDanhGia();
    }

    private void checkWeight() {
        if (this.tongDiem() != 100)
            throw new InvalidWeightException("Tỉ trọng chưa đạt đủ 100!");
        retypeOldWeight(); // nhap lai nhung cot diem truoc
        handleWeightInput();
        retypeUntilMatch100();
    }

    private void retypeUntilMatch100() {
        boolean isRepeated = true;
        do {
            try {
                checkWeight(); // kiem tra ti trong tong == 100 chua,
                // neu chua -> throw InvalidWeightException
                isRepeated = false;
            } catch (InvalidWeightException e) {
                System.out.println("Tổng tỉ trọng phải là 100!");
                System.out.println("Vui lòng nhập lại");
                retypeOldWeight();
            }
        } while (isRepeated);
    }

    // xoa hinh thuc danh gia
    public void xoaCotDiem(CotDiem cotDiem) throws
            MinSizeExceededException {
        this.cotDiem.remove(cotDiem);
    }

    public void xoaCotDiem()
            throws MinSizeExceededException, IllegalArgumentException {
        System.out.println("Danh sách cột điểm hiện tại");
        this.cotDiem.forEach(System.out::println);
        System.out.print("Nhập tên cột điểm cần xóa: ");
        String tenCotDiem = sc.nextLine();
        var cotDiemCanXoa = this.cotDiem.stream()
                .filter(cotDiem1 -> cotDiem1.getTenDiem().equalsIgnoreCase(tenCotDiem))
                .findFirst().orElse(null);

        if (cotDiemCanXoa == null) throw new IllegalArgumentException("Tên cột điểm không đúng");
        else {
            this.xoaCotDiem(cotDiemCanXoa);
            retypeOldWeight();
            retypeUntilMatch100();
        }
    }

    @Override
    public String toString() {
        return SEPARATOR + String.format("|%-12s|%-45s|%-35s|%-10s|\n", "Bài đánh giá", " Nội dung", " Phương pháp", " Tỉ trọng") +
                this.cotDiem.stream().map(CotDiem::toString).collect(Collectors.joining()) +
                SEPARATOR;
    }
}