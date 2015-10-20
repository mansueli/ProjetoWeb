/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utfpr.projetoweb.utils;

/**
 *
 * @author Rodrigo
 */
public class ServletUtils {

    private ServletUtils() {
    }

    public static int getIntParameterValue(String requestURL) {
        String temps = keepNumbers(requestURL);
        System.out.println(temps);
        if (isNumeric(temps)) {
            int postID = Integer.parseInt(temps);
            System.out.println("postID == " + postID);
            return postID;
        }
        return -1;
    }

    private static String keepNumbers(String s) {
        return s.replaceAll("\\D", "").trim();
    }

    public static boolean isNumeric(String str) {
        try {
            Long l = Long.parseLong(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
