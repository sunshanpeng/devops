package com.sunshanpeng.devops.common.core.util;

import org.springframework.util.Assert;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    /**
     * Encodes a string 2 MD5
     *
     * @param content String to encode
     * @return Encoded String
     * @throws NoSuchAlgorithmException
     */
    public static String crypt(String content) throws NoSuchAlgorithmException {
        Assert.hasText(content, "content to encrypt cannot be empty");
        StringBuilder hexString = new StringBuilder();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(content.getBytes());
        byte[] hash = md.digest();
        for (byte hash1 : hash) {
            if ((0xff & hash1) < 0x10) {
                hexString.append("0").append(Integer.toHexString((0xFF & hash1)));
            } else {
                hexString.append(Integer.toHexString(0xFF & hash1));
            }
        }
        return hexString.toString();
    }

}
