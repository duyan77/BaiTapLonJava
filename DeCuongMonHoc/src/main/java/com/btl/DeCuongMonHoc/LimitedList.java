package com.btl.DeCuongMonHoc;

import java.util.ArrayList;
import java.util.Collection;

public class LimitedList<T> extends ArrayList<T> {
    private final int maxSize;
    private final int minSize;

    public LimitedList(int maxSize) {
        this(0, maxSize);
    }

    public LimitedList(int minSize, int maxSize) {
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    @Override
    public boolean add(T t) {
        if (this.size() >= this.maxSize)
            throw new MaxSizeExceededException("Không thể thêm vi đã " +
                    "đạt đến kích thước tối đa!");
        return super.add(t);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (this.size() + c.size() > this.maxSize)
            throw new MaxSizeExceededException("Không thể thêm vi đã " +
                    "đạt đến kích thước tối đa!");
        return super.addAll(c);
    }

    @Override
    public boolean remove(Object o) {
        if (this.size() <= this.minSize)
            throw new MinSizeExceededException("Không thể xóa vì đã " +
                    "đạt đến kích thước tối thiểu!");
        return super.remove(o);
    }

    @Override
    public T remove(int index) {
        if (this.size() - 1 < this.minSize)
            throw new MinSizeExceededException("Không thể xóa vì đã đạt " +
                    "đến kích thước tối thiểu!");
        return super.remove(index);
    }
}
