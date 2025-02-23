package com.schoolused.until;

import java.util.List;

public class page<T> {
    private int page;//分页起始页
    private int size;//每页长度
    private List<T> data;//返回记录集合
    private long total;//总过记录条数

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "page{" +
                "page=" + page +
                ", size=" + size +
                ", data=" + data +
                ", total=" + total +
                '}';
    }
}
