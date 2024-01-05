package com.btl.DeCuongMonHoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhanCong {

    private final List<GiangVien> phanCongGV = new ArrayList<>();

    public void themGiangVien(GiangVien giangVien) {
        this.phanCongGV.add(giangVien);
    }

    public void themGiangVien(){
        GiangVien giangVien = new GiangVien();
        giangVien.nhapGiangVien();
        this.themGiangVien(giangVien);
    }

    public GiangVien timGiangVien(String maGV) {
        return this.phanCongGV.stream().filter(giangVien -> giangVien.getMaGV().equals(maGV))
                .findFirst().orElse(null);
    }

    public static List<GiangVien> docTuFile(String tenFile){
        List<GiangVien> giangVienList = new ArrayList<>();
        QuanLyDeCuong quanLyDeCuong = new QuanLyDeCuong();
        FileReader fr = null;
        BufferedReader br = null;
        //doc file GiangVien
        try {
            fr = new FileReader(tenFile);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine())!=null){
                String[] info = line.split("[|]");
                String maGV = info[0].trim();
                String tenGV = info[1].trim();
                int soMon = Integer.parseInt(info[2].trim());
                for(int i = 0; i<soMon; i++)
                {
                    String maMon = info[i+3].trim();
                    quanLyDeCuong.timMonHoc(maMon);

                }
                GiangVien gv = new GiangVien(maGV);
                giangVienList.add(gv);
            }
        } catch (IOException e) {
            System.out.println("loi ghi file");
        }finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                System.out.println("Loi dong file");
            }
        }
        return giangVienList;
    }

    // danh sach de cuong cua giang vien
    public List<DeCuongMonHoc> danhSachDeCuong(String id) {
        return this.phanCongGV.stream()
                .filter(giangVien -> giangVien.getMaGV().equals(id))
                .findFirst().orElseThrow().danhSachDeCuong();
    }
}

