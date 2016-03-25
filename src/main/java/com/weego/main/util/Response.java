package com.weego.main.util;

/**
 * Created by liuniandxx on 16-3-24.
 */
public class Response<T> {

    private boolean status;

    private T data;

    public Response(boolean status, T data) {
        this.status = status;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

