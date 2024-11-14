package com.agarwood.webchatapp.until;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密类,传入字符串,返回32长度的字符串
 */
public class MD5Until {
    public static String encrypt(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
