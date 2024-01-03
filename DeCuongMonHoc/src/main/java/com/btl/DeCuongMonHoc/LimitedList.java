package com.btl.DeCuongMonHoc;

import java.util.ArrayList;

public class LimitedList<T> extends ArrayList<T> {
    private final int maxSize;

    public LimitedList(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public boolean add(T t) {
        if (this.size() >= this.maxSize) {
            throw new IllegalArgumentException("Không thể thêm: Set đã đạt đến kích thước tối đa!");
        }
        return super.add(t);
    }
}
