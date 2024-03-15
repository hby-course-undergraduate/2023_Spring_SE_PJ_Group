package com.group4.eshopping.exception;


public class CheckException extends RuntimeException {
    private int code;
    private String wrongMsg;

    public CheckException(int code, String wrongMsg) {
        setCode(code);
        setWrongMsg(wrongMsg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getWrongMsg() {
        return wrongMsg;
    }

    public void setWrongMsg(String wrongMsg) {
        this.wrongMsg = wrongMsg;
    }

}
