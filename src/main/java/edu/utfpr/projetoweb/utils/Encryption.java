/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utfpr.projetoweb.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Rodrigo
 */
public final class Encryption {
    
    private Encryption(){};//static class
    
    public static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
    
    public static String getUserHash(String password, int salt) throws NoSuchAlgorithmException{
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(salt));
        sb.append(password);
        sb.append(Integer.toString(salt%2));
        return sha1(sb.toString());
    }
    
    
}
