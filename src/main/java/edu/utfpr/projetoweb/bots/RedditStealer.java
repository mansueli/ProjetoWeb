/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utfpr.projetoweb.bots;

import edu.utfpr.projetoweb.entities.PostEntity;
import edu.utfpr.projetoweb.entities.UserEntity;
import edu.utfpr.projetoweb.repositories.PostRepository;
import edu.utfpr.projetoweb.repositories.UserRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Rodrigo
 */
public class RedditStealer {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */

    public static void main(String[] args) {
        UserRepository userRepository = UserRepository.getInstance();
        PostRepository postRepository = PostRepository.getInstance();
        String subreddit = "funny";
        String username = subreddit + "doood";
        UserEntity user = new UserEntity(username, username + "@example.com", "", "4321");
        //userRepository.save(user);
        user = userRepository.findbyUsername(username);
        String jsonreturn = getJSONString("http://www.reddit.com/r/" + subreddit + "/.json?limit=90");
        
        System.out.println(jsonreturn);
        String[] array = jsonreturn.split("\"domain\"");
        boolean first = true;
        for (String str : array) {
            if (first) {
                first = !first;
            } else {
                String title = getField("title", str);
                int likes = (int) (Math.random() * 300);
                String url = getURL(str);
                String validatedURL = urlChecker(url);
                if (!validatedURL.isEmpty()) {
                    PostEntity post = new PostEntity(user, title, validatedURL, likes, "comic");//subreddit);
                    postRepository.save(post);
                }
            }
        }
    }

    private static String urlChecker(String url) {
        if (url.endsWith("jpg") || url.endsWith("png") || url.endsWith("gif")
                || url.endsWith("JPG") || url.endsWith("PNG") || url.endsWith("GIF")) {
            return url;
        }
        if (url.startsWith("http://imgur.com/") || url.startsWith("https://imgur.com/")) {
            String newurl = url.replace("imgur.com", "i.imgur.com");
            newurl += ".png";
            if (newurl.contains("galle")) {
                return "";
            }
            return newurl;
        }
        return "";
    }

    @SuppressWarnings({"BroadCatchBlock", "TooBroadCatch", "UseSpecificCatch"})
    public static String getJSONString(String query) {
        String rand;
        try {
            rand = get(query);
            return rand;
        } catch (Exception e) {
        }
        System.out.println("Failed to get from Random.org (getRand)");
        return "";
    }

    public static String getField(String field, String json) {
        final int startson = 4 + field.length();
        int indexInit = json.indexOf(field) + startson;
        int indexFinal = json.indexOf('\"', indexInit);
        return json.substring(indexInit, indexFinal);
    }

    public static String getURL(String json) {
        final int startson = json.indexOf("\"saved\":false\"");

        int indexInit = json.indexOf("url", startson) + 5;
        int indexFinal = json.indexOf("\"", indexInit);
        String oi = json.substring(indexInit, indexFinal);
        do {
            indexInit = json.indexOf("url", indexFinal) + 7;
            indexFinal = json.indexOf("\"", indexInit);
            oi = json.substring(indexInit, indexFinal);
        } while (oi.contains("reddit") == true);
        return oi;
    }//

    @SuppressWarnings({"BroadCatchBlock", "TooBroadCatch", "UseSpecificCatch"})
    public static String get(String urlString) throws IOException {
        HttpURLConnection connection;
        BufferedReader br = null;
        String json = "";
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            json = sb.toString();
        } catch (Exception e) {
            return null;
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return json;
    }

}
