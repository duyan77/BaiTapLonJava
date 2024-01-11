package com.btl.DeCuongMonHoc;

import java.util.Comparator;

/**
 * Comparator for DeCuongMonHoc.
 * Sắp xếp theo thứ tự giảm dần theo số tín chỉ và theo thứ tự tăng dần theo mã khóa học cho các khóa học có cùng số tín chỉ.
 */

public class DeCuongComparator implements Comparator<DeCuongMonHoc> {
    @Override
    public int compare(DeCuongMonHoc o1, DeCuongMonHoc o2) {
        int tc1 = o1.getMonHoc().getSoTinChi();
        int tc2 = o2.getMonHoc().getSoTinChi();

        if (tc1 == tc2)
            return o1.getMonHoc().getMa() - o2.getMonHoc().getMa();
        return tc2 - tc1;
    }
}
