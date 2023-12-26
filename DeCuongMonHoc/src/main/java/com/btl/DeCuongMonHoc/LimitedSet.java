package com.btl.DeCuongMonHoc;

import java.util.HashSet;

public class LimitedSet<T> extends HashSet<T> {
    private final int maxSize;

    public LimitedSet(int maxSize) {
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
