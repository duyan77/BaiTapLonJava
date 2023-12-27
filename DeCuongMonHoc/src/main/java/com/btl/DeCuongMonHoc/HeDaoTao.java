package com.btl.DeCuongMonHoc;

import java.util.HashMap;
import java.util.Map;

public enum HeDaoTao {

    CHINH_QUY("Chính quy"),

    LIEN_THONG("Liên thông");

    private final String nameOfHeDaoTao;

    HeDaoTao(String nameOfHeDaoTao) {
        this.nameOfHeDaoTao = nameOfHeDaoTao;
    }

    @Override
    public String toString() {
        return this.nameOfHeDaoTao;
    }

    private static final Map<Integer, HeDaoTao> map = new HashMap<>();

    static {
        map.put(1, CHINH_QUY);
        map.put(2, LIEN_THONG);
    }

    // k = key
    public static HeDaoTao convertIntToHeDaoTao(int k) {
        return map.get(k);
    }
}
