package com.tc.json.utils;

/**
 * @author tc
 * @date 2023/6/19
 * @email tiancai@citycloud.com.cn
 */
public class StringUtils {

    public static boolean isBlank(String keyStr) {

        return keyStr == null || keyStr.length() < 1;

    }

    public static boolean isNotBlank(String keyStr) {
        return !isBlank(keyStr);
    }
}
