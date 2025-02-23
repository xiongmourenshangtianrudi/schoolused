package com.schoolused.controler;

public class Result {
    private int code;
    private Object data;
    private String Token;
    private String msg;

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public Result(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public void setToken(String token) {
        Token = token;
    }

    public Result(int code, Object data,String Token, String msg) {
        this.code = code;
        this.data = data;
        this.Token =Token;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getToken() {
        return Token;
    }

}
