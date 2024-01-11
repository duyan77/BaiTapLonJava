package com.btl.DeCuongMonHoc;

public enum MonDieuKien {
    MON_TIEN_QUYET {
        @Override
        public void themMonDieuKien(MonHoc m, MonHoc monCanThem) {
            if (m.dsMonTienQuyet().contains(monCanThem)) {
                System.out.println("Môn " + monCanThem.getTen() +
                        " đã có trong danh sách môn tiên quyết");
                return;
            }
            m.dsMonTienQuyet().add(monCanThem);
        }

        @Override
        public String tenHeDaoTao() {
            return "môn tiên quyết";
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
                System.out.println("Môn " + monCanThem.getTen() +
                        " đã có trong danh sách môn học trước");
                return;
            }
            m.dsMonHocTruoc().add(monCanThem);
        }

        @Override
        public String tenHeDaoTao() {
            return "môn học trước";
        }

        @Override
        public void xoaMonDieuKien(MonHoc m, MonHoc monCanXoa) {
            m.dsMonTienQuyet().remove(monCanXoa);
        }
    };

    public abstract void themMonDieuKien(MonHoc m, MonHoc monCanThem);

    public abstract String tenHeDaoTao();

    public abstract void xoaMonDieuKien(MonHoc m, MonHoc monCanXoa);
}