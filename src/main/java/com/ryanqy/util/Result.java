package com.ryanqy.util;

import lombok.Getter;

/**
 * @author tong.wu
 * created on 2023/12/12
 */
@Getter
public class Result<T> {

    private final Integer code;

    private final String message;

    private final T data;

    private Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCodeEnum.OK.getCode(), ResultCodeEnum.OK.getMessage(), data);
    }

    public static <T> Result<T> success() {
        return new Result<>(ResultCodeEnum.OK.getCode(), ResultCodeEnum.OK.getMessage(), null);
    }

    public static <T> Result<T> failed(ResultCodeEnum resultCode) {
        return new Result<>(resultCode.getCode(), resultCode.getMessage(), null);
    }

    public static <T> Result<T> failed(ResultCodeEnum resultCode, String message) {
        return new Result<>(resultCode.getCode(), message, null);
    }

}
