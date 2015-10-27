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
        String subreddit = "meme";
        String username = subreddit + "doge";
        //UserEntity(String username, String email, String avatarURL, String password){
        UserEntity user = new UserEntity(username, username + "@example.com", "", "4321");
        userRepository.save(user);
        user = userRepository.findbyUsername(username);
        String jsonreturn = getJSONString("http://www.reddit.com/r/" + subreddit + "/.json?limit=99");
        String[] array = jsonreturn.split("\"domain\"");
        boolean first = true;
        for (String str : array) {
            if (first) {
                first = !first;
            } else {
                //System.out.println(str);
                String title = getField("title", str);
                int likes = (int) (Math.random() * 300);
                String url = getURL(str);
                //System.out.println(title + " " + likes + " " + url);
                String validatedURL = urlChecker(url);
                //public PostEntity(UserEntity user, String title, String imgURL, long likes, String category) {
                if (!validatedURL.isEmpty()) {
                    PostEntity post = new PostEntity(user, title, validatedURL, likes, subreddit);
                    postRepository.save(post);
                }
            }
        }
    }

    private static String urlChecker(String url) {
        if (url.endsWith("jpg") || url.endsWith("png") || url.endsWith("gif")
                || url.endsWith("JPG") || url.endsWith("PNG") || url.endsWith("GIF")) {
            System.out.println(url);
            return url;
        }
        if (url.startsWith("http://imgur.com/") || url.startsWith("https://imgur.com/")) {
            String newurl = url.replace("imgur.com", "i.imgur.com");
            newurl += ".png";
            System.out.println(newurl);
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
            //Logger.getLogger(RandomOrg.class.getName()).log(Level.SEVERE, null, e);
        }
        System.out.println("Failed to get from Random.org (getRand)");
        return "";
    }

    public static String getField(String field, String json) {
        final int startson = 4 + field.length();
        int indexInit = json.indexOf(field) + startson;
        int indexFinal = json.indexOf('\"', indexInit);
//        System.out.println(json.substring(indexInit));
//        System.out.println(json.substring(indexInit, indexFinal));
        return json.substring(indexInit, indexFinal);
        //num_reports
    }

    public static String getURL(String json) {
        final int startson = json.indexOf("\"saved\":false\"");

        int indexInit = json.indexOf("url", startson) + 5;
        int indexFinal = json.indexOf("\"", indexInit);
//        System.out.println("url:");
//        System.out.println(json.substring(indexInit));
        String oi = json.substring(indexInit, indexFinal);
        do {
            indexInit = json.indexOf("url", indexFinal) + 7;
            indexFinal = json.indexOf("\"", indexInit);
//            System.out.println("url:");
            oi = json.substring(indexInit, indexFinal);
//            System.out.println(oi);
        } while (oi.contains("reddit") == true);
        return oi;
        //num_reports
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
                //System.out.println(line);
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
