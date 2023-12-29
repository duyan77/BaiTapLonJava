package com.btl.DeCuongMonHoc;

import java.util.HashMap;
import java.util.Map;

public enum HeDaoTao {

    CHINH_QUY() {
        @Override
        public String tenHeDaoTao() {
            return "Chính quy";
        }
    },

    LIEN_THONG() {
        @Override
        public String tenHeDaoTao() {
            return "Liên thông";
        }
    };

    private static final Map<Integer, HeDaoTao> map = new HashMap<>();

    static {
        map.put(1, CHINH_QUY);
        map.put(2, LIEN_THONG);
    }

    // k = key
    public static HeDaoTao convertIntToHeDaoTao(int k) {
        return map.get(k);
    }

    public abstract String tenHeDaoTao();
}
