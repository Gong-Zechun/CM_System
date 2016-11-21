package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * properties文件处理
 * @author: Raygong
 * @date: 2016/11/20 10:58.
 */
public class PropertiesReaderUtil {
    /**
     * properties文件读取（读取的文件路径在resources文件夹下）
     * @param fileName
     * @return
     * @throws IOException
     */
    public static Properties reader(String fileName) throws IOException {
        InputStream in = PropertiesReaderUtil.class.getClassLoader().getResourceAsStream(fileName);
        Properties prop = new Properties();
        prop.load(in);
        return prop;
    }

    public static void main(String[] args) {
        try {
            reader("jdbc.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
