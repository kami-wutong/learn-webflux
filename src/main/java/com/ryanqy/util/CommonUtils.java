package com.ryanqy.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author tong.wu
 * created on 2023/12/20
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonUtils {

    public static ServiceException newServiceException(String message) {
        return new ServiceException(message);
    }

}
