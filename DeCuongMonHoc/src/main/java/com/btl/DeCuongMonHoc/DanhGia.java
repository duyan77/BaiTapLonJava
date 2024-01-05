package com.btl.DeCuongMonHoc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static com.btl.DeCuongMonHoc.CauHinh.*;

public class DanhGia {

    private List<CotDiem> cotDiem = new LimitedList<>(soCotDiemToiThieu, soCotDiemToiDa); // cot diem toi thieu
    // 2 va toi da 4

    public DanhGia() {
    }

    public DanhGia(List<CotDiem> cotDiem) {
        this.cotDiem = cotDiem;
        CotDiem.setDem(this.cotDiem.size());
    }

    // getter, setter
    public List<CotDiem> getCotDiem() {
        return cotDiem;
    }

//-------------------------------------------------------------------------------------------

    public int soCotDiemHienTai() {
        return this.cotDiem.size();
    }

    public void nhapDanhGia() {
        int soCot = getInt();
        this.handleWeightInput();

        while (this.tongDiem() != 100) {
            System.out.println("Tong ti trong phai la 100!");
            System.out.println("Vui long nhap lai ti trong phu hop cho cac cot diem");
            this.retypeWeight();
        }
    }

    private void handleWeightInput() {
        int soCot;
        do {
            System.out.print("Nhập số cột điểm muon them moi: ");
            soCot = getInt();
            if (soCot + this.soCotDiemHienTai() < soCotDiemToiThieu ||
                    soCot + this.soCotDiemHienTai() > soCotDiemToiDa)
                System.out.printf("""
                        Số cột điểm tối thiểu %d và tối đa %d!
                        Vui lòng nhập lại
                        So cot diem hien tai la: %d
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
                    System.out.println("Vui long nhap lai gia tri phu hop!");
                }
            } while (isRepeated);
        }
    }

    private void retypeWeight() {
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

    public void themCotDiem(CotDiem... cotDiems)
            throws MaxSizeExceededException, IllegalArgumentException {
        this.cotDiem.addAll(Arrays.asList(cotDiems));
        CotDiem.setDem(this.cotDiem.size());
    }

    private void checkWeight() {
        if (this.tongDiem() != 100)
            throw new InvalidWeightException("Ti trong chua dat du 100!");
    }

    public void themCotDiem() throws
            MaxSizeExceededException, IllegalArgumentException, InvalidWeightException {

        retypeWeight(); // nhap lai nhung cot diem truoc
        handleWeightInput();

        boolean isRepeated = true;
        do {
            try {
                checkWeight(); // kiem tra ti trong tong == 100 chua, neu chua -> throw InvalidWeightException
                isRepeated = false;
            } catch (InvalidWeightException e) {
                System.out.println("Tong ti trong phai la 100!");
                System.out.println("Vui long nhap lai");
                retypeWeight();
            }
        } while (isRepeated);
    }

    // xoa hinh thuc danh gia
    public void xoaCotDiem(CotDiem cotDiem) throws
            MinSizeExceededException {
        this.cotDiem.remove(cotDiem);
    }

    public void xoaCotDiem(int index) throws MinSizeExceededException {
        this.cotDiem.remove(index);
    }

    public void xoaCotDiem()
            throws MinSizeExceededException, IllegalArgumentException {
        System.out.println("Danh sach cot diem hien tai");
        this.cotDiem.forEach(System.out::println);
        System.out.println("Nhap ten cot diem can xoa: ");
        String tenCotDiem = sc.nextLine();
        var cotDiemCanXoa = this.cotDiem.stream().filter(cotDiem1 ->
                        cotDiem1.getTenDiem().equalsIgnoreCase(tenCotDiem))
                .findFirst().orElse(null);
        if (cotDiemCanXoa == null) {
            throw new IllegalArgumentException("Ten cot diem khong dung");
        } else {
            this.xoaCotDiem(cotDiemCanXoa);
        }
    }

    @Override
    public String toString() {
        return this.cotDiem.stream().map(CotDiem::toString).collect(Collectors.joining());
    }
}