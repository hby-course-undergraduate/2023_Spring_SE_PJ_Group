package com.group4.eshopping.exception;

public class OnlyForException extends RuntimeException {
    private String wrongMsg;

    public OnlyForException() {
    }

    public OnlyForException(String wrongMsg) {
        setWrongMsg(wrongMsg);
    }

    public String getWrongMsg() {
        return wrongMsg;
    }

    public void setWrongMsg(String wrongMsg) {
        this.wrongMsg = wrongMsg;
    }
}
