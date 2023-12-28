package com.ryanqy.util;

/**
 * @author tong.wu
 * created on 2023/12/18
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable throwable) {
        super(throwable.getMessage());
    }

}
