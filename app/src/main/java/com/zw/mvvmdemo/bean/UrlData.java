package com.zw.mvvmdemo.bean;

public class UrlData<T> {

    private T data;
    private String errorMsg;

    public UrlData(){}

    public UrlData(T data, String errorMsg) {
        this.data = data;
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
