package com.demo.android.bean;

/**
 * Created by jinxh on 16/2/17.
 */
public class ResponseBean<T> {
    private boolean succ;
    private T results;
    private String msg;
    private int count;
    private int page;
    private int TotalPages;
    private int TotalRowsCount;

    public int getTotalPages() {
        return TotalPages;
    }

    public void setTotalPages(int totalPages) {
        TotalPages = totalPages;
    }

    public int getTotalRowsCount() {
        return TotalRowsCount;
    }

    public void setTotalRowsCount(int totalRowsCount) {
        TotalRowsCount = totalRowsCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSucc() {
        return succ;
    }

    public void setSucc(boolean succ) {
        this.succ = succ;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
