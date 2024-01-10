package com.btl.DeCuongMonHoc;

public enum MonDieuKien {
    MON_TIEN_QUYET {
        @Override
        public void themMonDieuKien(MonHoc m, MonHoc monCanThem) {
            if (m.dsMonTienQuyet().contains(monCanThem)) {
                System.out.println("Mon " + monCanThem.getTen() +
                        " da co trong danh sach mon tien quyet");
                return;
            }
            m.dsMonTienQuyet().add(monCanThem);
        }

        @Override
        public void xoaMonDieuKien(MonHoc m, MonHoc monCanXoa) {
            m.dsMonTienQuyet().remove(monCanXoa);
        }
    },
    MON_HOC_TRUOC {
        @Override
        public void themMonDieuKien(MonHoc m, MonHoc monCanThem) {
            if (m.dsMonHocTruoc().contains(monCanThem)) {
                System.out.println("Mon " + monCanThem.getTen() +
                        " da co trong danh sach mon hoc truoc");
                return;
            }
            m.dsMonHocTruoc().add(monCanThem);
        }

        @Override
        public void xoaMonDieuKien(MonHoc m, MonHoc monCanXoa) {
            m.dsMonTienQuyet().remove(monCanXoa);
        }
    };

    public abstract void themMonDieuKien(MonHoc m, MonHoc monCanThem);

    public abstract void xoaMonDieuKien(MonHoc m, MonHoc monCanXoa);
}