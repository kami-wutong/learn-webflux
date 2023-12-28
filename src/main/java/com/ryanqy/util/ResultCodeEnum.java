package com.ryanqy.util;

import lombok.Getter;

/**
 * @author tong.wu
 * created on 2023/12/28
 */
@Getter
public enum ResultCodeEnum {

    OK(200, "OK"),

    UNAUTHORIZED(401, "Unauthorized"),

    FORBIDDEN(403, "Forbidden"),;

    private final Integer code;

    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}



