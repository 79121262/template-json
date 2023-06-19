package com.tc.json.utils;

/**
 * @author tc
 * Date 2023/6/19
 * Email 79121262@qq.com
 */
public class StringUtils {

    public static boolean isBlank(String keyStr) {

        return keyStr == null || keyStr.length() < 1;

    }

    public static boolean isNotBlank(String keyStr) {
        return !isBlank(keyStr);
    }
}
