package com.wanxi.result;

public enum ResultTool {
    SUCCESS(0, "SUCCESS"),
    ERROR(1, "ERROR"),
    NO_LOGIN(2, "未登录"),
    EXIPERS(3, "session已过期，重新登录");

    private String msg;
    private Integer code;

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

    ResultTool(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
