package com.wanxi.tool;

/**
 * 枚举了一些常用API操作码
 *
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(0, "操作成功"),
    FAILED(1, "操作失败"),
    VALIDATE_FAILED(400, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");
    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
