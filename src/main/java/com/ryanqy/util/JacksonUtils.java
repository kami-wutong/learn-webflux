package com.ryanqy.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

/**
 * @author tong.wu
 * created on 2023/11/13
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JacksonUtils {

    @Setter
    private static ObjectMapper objectMapper;

    public static ObjectMapper getObjectMapper() {
        return Objects.isNull(objectMapper) ? new ObjectMapper() : objectMapper;
    }

    public static String writeValueAsString(Object value) {
        try {
            return getObjectMapper().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error("write value as string failed, value:{}", value, e);
            throw new ServiceException(e);
        }
    }

    public static <T> T readValue(String content, Class<T> type) {
        try {
            return getObjectMapper().readValue(content, type);
        } catch (IOException e) {
            log.error("read value failed, content:{}, type:{}", content, type, e);
            throw new ServiceException(e);
        }
    }

    public static <T> T readValue(String content, TypeReference<T> valueTypeRef) {
        try {
            return getObjectMapper().readValue(content, valueTypeRef);
        } catch (IOException e) {
            log.error("read value failed, content:{}, valueTypeRef:{}", content, valueTypeRef, e);
            throw new ServiceException(e);
        }
    }

}
