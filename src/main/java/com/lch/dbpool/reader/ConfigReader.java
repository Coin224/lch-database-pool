package com.lch.dbpool.reader;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 这个类是专门来读取配置文件的
 */
public class ConfigReader {


    private static Properties prop = new Properties();

    /*用一个Map来缓存读取到的文件信息*/
    private static Map<String,String> jdbcMap = new HashMap<>();


    // 获取到全部的文件信息，存入到map集合中
    static {
        try {
            // 加载配置文件
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
            // 获取到全部的key
            Enumeration enumeration = prop.propertyNames();
            while (enumeration.hasMoreElements()) {
                String key = (String) enumeration.nextElement();
                String value = prop.getProperty(key);
                jdbcMap.put(key,value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 获取值的方法
    public static String getValue(String key) {
        return jdbcMap.get(key);
    }
}
