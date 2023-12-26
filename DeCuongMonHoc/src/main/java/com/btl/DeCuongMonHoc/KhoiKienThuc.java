package com.btl.DeCuongMonHoc;

public enum KhoiKienThuc {

    CO_SO() {
        @Override
        public String toString() {
            return "Cơ sở";
        }
    },

    CO_SO_NGANH() {
        @Override
        public String toString() {
            return "Cơ sở ngành";
        }
    },

    CHUYEN_NGANH() {
        @Override
        public String toString() {
            return "Chuyên ngành";
        }
    };

    //    phương thức trả về loại kiến thức của môn học đó
    public static KhoiKienThuc convertIntToKienThuc(int k) {
        if (k == 1) return CO_SO;
        else if (k == 2) return CO_SO_NGANH;
        else if (k == 3) return CHUYEN_NGANH;
        return null;
    }
}
