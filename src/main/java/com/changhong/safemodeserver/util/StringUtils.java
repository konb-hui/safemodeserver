package com.changhong.safemodeserver.util;

import org.apache.tomcat.util.security.MD5Encoder;

/**
 * @author konb
 */
public class StringUtils {

    public static String encrypt(String data) {
        String cipherText = MD5Encoder.encode(stuff(data, 16-data.length()).getBytes());
        return cipherText;
    }

    public static String stuff(String data, int length) {
        if (length <= 0) {
            return data;
        }
        StringBuilder stringBuilder = new StringBuilder(data);
        for (int i = 0; i < length; i ++) {
            if (i < 10) {
                stringBuilder.append(i);
            } else {
                stringBuilder.append(i - 10);
            }
        }
        return stringBuilder.toString();

    }

}
