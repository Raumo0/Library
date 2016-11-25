package com.netcracker.library.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by raumo0 on 25.11.16.
 */
public class PasswordGenerator {
    private PasswordGenerator(){}

    public static PasswordGenerator getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public String generatePassword(String password, String salt) throws NoSuchAlgorithmException {
        String first = md5Digest(password);
        return md5Digest(first + salt);
    }

    public String generateSalt(String password) throws NoSuchAlgorithmException {
        String first = md5Digest(password);
        return md5Digest(first.substring(0, first.length()/2));
    }

    private String md5Digest(String original) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(original.getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    private static class SingletonHolder{
        private static final PasswordGenerator INSTANCE = new PasswordGenerator();
    }
}
