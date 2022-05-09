package com.ycj.mall.common.lang;

import java.io.Serializable;

// Json格式的数据进行响应
public class Result<E> implements Serializable {
    private Integer code;
    private String msg;
    private E data;

    public Result() {
    }

    public Result(Integer code) {
        this.code = code;
    }

    public Result(Throwable e) {
        this.msg = e.getMessage();
    }

    public Result(Integer code, E data) {
        this.code = code;
        this.data = data;
    }

    public static Result succ(Object data) {
        Result result = new Result();
        result.setCode(200);
        result.setMessage("");
        result.setData(data);
        return result;
    }

    public static Result succ(Integer code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(msg);
        result.setData(data);
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
