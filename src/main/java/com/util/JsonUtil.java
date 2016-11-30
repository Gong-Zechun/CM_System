package com.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

/**
 * Json处理工具类
 * @author AllenGong
 * @version V1.0
 * @date 2016-10-19 16:44
 */
public class JsonUtil {
    public static final ObjectMapper JSON_MAPPER_NOTNULL = new ObjectMapper();

    static {
        JSON_MAPPER_NOTNULL.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        JSON_MAPPER_NOTNULL.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        JSON_MAPPER_NOTNULL.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        JSON_MAPPER_NOTNULL.setLocale(Locale.getDefault());
    }

    /**
     * 把JavaBean转换为json字符串
     *      普通对象转换：toJson(Student)
     *      List转换：toJson(List)
     *      Map转换:toJson(Map)
     * 我们发现不管什么类型，都可以直接传入这个方法
     *
     * @param object
     *            JavaBean对象
     * @return json字符串
     */
    public static String toJsonStr(Object object) {
        try {
            return JSON_MAPPER_NOTNULL.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T readValue(String content, Class<T> valueType) {
        try {
            return JSON_MAPPER_NOTNULL.readValue(content, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json字符串转为Map<String, Map<String, String>>
     * @param content 传入的json字符串
     * @return
     */
    public static Map<String, Map<String, String>> json2Map(String content) {
        try {
            return JSON_MAPPER_NOTNULL.readValue(content, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
