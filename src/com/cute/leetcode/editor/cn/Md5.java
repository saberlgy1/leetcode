package com.cute.leetcode.editor.cn;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @program: leetcode
 * @description:
 * @author: lgy
 * @create: 2020-06-09 16:13
 **/

public class Md5 {
    public static void main(String[] args) {
        System.out.println(encrypt("123456", "MD5"));
    }

    public static String encrypt(String message, String algorithmName) {
        MessageDigest messageDigest = null;
        try {
            if (algorithmName == null) algorithmName = "MD5";
            messageDigest = MessageDigest.getInstance(algorithmName);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.reset();
        byte[] bytes = message.getBytes();
        byte[] out = messageDigest.digest(bytes);
        BASE64Encoder enc = new BASE64Encoder();
        return enc.encode(out);
    }
}

