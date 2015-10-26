/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utfpr.projetoweb.utils;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


    public static String getCompleteURL(HttpServletRequest request) {
        StringBuffer requestURL = request.getRequestURL();
        if (request.getQueryString() != null) {
            requestURL.append("?").append(request.getQueryString());
        }
        String completeURL = requestURL.toString();
        return completeURL;
    }
    public static void printError(HttpServletRequest request, HttpServletResponse response, String error) throws ServletException, IOException{
        request.setAttribute("error", error);
        RequestDispatcher view = request.getRequestDispatcher("jsp/erro.jsp");
        view.forward(request, response);
    }

}
