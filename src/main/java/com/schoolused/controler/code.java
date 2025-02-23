package com.schoolused.controler;

import org.springframework.stereotype.Component;


public  class code {
    static int REQUEST_OK = 200;
    static int REQUEST_ERRO = 201;

    public static int getRequestOk() {
        return REQUEST_OK;
    }

    public static void setRequestOk(int requestOk) {
        REQUEST_OK = requestOk;
    }

    public static int getRequestErro() {
        return REQUEST_ERRO;
    }

    public static void setRequestErro(int requestErro) {
        REQUEST_ERRO = requestErro;
    }
}
