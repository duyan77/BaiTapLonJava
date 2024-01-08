package com.btl.DeCuongMonHoc;

public enum MonDieuKien {
    MON_TIEN_QUYET {
        @Override
        public void themMonDieuKien(MonHoc m, MonHoc monDieuKien) {
            m.dsMonTienQuyet().add(monDieuKien);
        }

        @Override
        public void xoaMonDieuKien(MonHoc m, MonHoc monDieuKien) {
            m.dsMonTienQuyet().remove(monDieuKien);
        }
    },
    MON_HOC_TRUOC {
        @Override
        public void themMonDieuKien(MonHoc m, MonHoc monDieuKien) {
            m.dsMonHocTruoc().add(monDieuKien);
        }

        @Override
        public void xoaMonDieuKien(MonHoc m, MonHoc monDieuKien) {
            m.dsMonTienQuyet().remove(monDieuKien);
        }
    };

    public abstract void themMonDieuKien(MonHoc m, MonHoc monDieuKien);

    public abstract void xoaMonDieuKien(MonHoc m, MonHoc monDieuKien);
}