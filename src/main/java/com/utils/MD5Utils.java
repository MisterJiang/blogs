package com.utils;

import java.security.MessageDigest;

/**
 * 密码加密
 */
public class MD5Utils {
    /**利用MD5进行加密*/
    public static String EncoderByMd5(String str) {
        String re = null;
        byte encrypt[];
        try {
            byte[] tem = str.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("md5");
            md5.reset();
            md5.update(tem);
            encrypt = md5.digest();
            StringBuilder sb = new StringBuilder();
            for (byte t : encrypt) {
                sb.append(Integer.toHexString(t & 0xFF));
            }
            re = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return re;
    }
}
