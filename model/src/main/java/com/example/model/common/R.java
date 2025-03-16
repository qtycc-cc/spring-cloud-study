package com.example.model.common;

public class R {
    private Integer code;
    private String message;
    private Object data;

    public static R ok() {
        return new R(200, "success", null);
    }

    public static R errror() {
        return new R(500, "error", null);
    }

    public static R ok(String message, Object data) {
        return new R(200, message, data);
    }

    public static R error(String message) {
        return new R(500, message, null);
    }

    public R(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
