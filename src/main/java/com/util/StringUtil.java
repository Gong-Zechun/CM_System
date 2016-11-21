package com.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author AllenGong
 * @version V1.0
 * @date 2016-10-19 16:44
 */
public class StringUtil {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    public static final ObjectMapper JSON_MAPPER_NOTNULL = new ObjectMapper();

    public static String toJSONString(Object obj) {
        try {
            return JSON_MAPPER_NOTNULL.writeValueAsString(obj);
        } catch (Exception var2) {
            throw new IllegalArgumentException("Transforming（toJSONString） failed: " + var2.getMessage(), var2);
        }
    }
}
