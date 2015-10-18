/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utfpr.projetoweb.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * https://github.com/kyllopardiun/GammaLottery/blob/master/src/gammalottery/RandomOrg.java
 */
public class RandomOrg {

/**
 * Random.org Wrapper class
 *
 * @author:Rodrigo Mansueli Nunes  {@literal <mansueli@ualberta.ca>}
 * <a href="http://kyllo.com.br">kyllo.com.br</a>
 *
 * TODO implements specific catch for every case (be aware of the errors
 * from Website's answers such as error 503)
 **/
 private RandomOrg(){};
    /**
     *
     * @return a integer value between 0 and 1000.
     */
    @SuppressWarnings({"BroadCatchBlock", "TooBroadCatch", "UseSpecificCatch"})
    public static int getRand() {
        String rand;
        try {
            final String query = "https://www.random.org/integers/?format=plain&min=-1000000000&max=1000000000&num=1&base=10&col=1";
            rand = get(query);
            return Integer.parseInt(rand);
        } catch (Exception e) {
            //Logger.getLogger(RandomOrg.class.getName()).log(Level.SEVERE, null, e);
        }
        System.out.println("Failed to get from Random.org (getRand)");
        return (int) (Math.random() * Integer.MAX_VALUE);
    }

    /**
     *
     * @param urlString A website URL which responds with a Plain Text output
     * (UTF-8)
     * @return A String with the contents of the website
     * @throws IOException when it couldn´t load the page.
     */
    @SuppressWarnings({"BroadCatchBlock", "TooBroadCatch", "UseSpecificCatch"})
    public static String get(String urlString) throws IOException {
        HttpURLConnection connection;
        BufferedReader br = null;
        String number = "";
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            number = br.readLine();
        } catch (Exception e) {
            return null;
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return number;
    }
}

