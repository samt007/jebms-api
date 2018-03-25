package com.jebms.comm.entity;

public enum ResultEntityCodeEnum {

    SUCCESS("0"),
    ERROR("2"),
    VALID_ERROR("1000");

    private String code;

    ResultEntityCodeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
