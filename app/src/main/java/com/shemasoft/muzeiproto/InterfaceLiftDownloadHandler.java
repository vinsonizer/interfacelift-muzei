package com.shemasoft.muzeiproto;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jv on 6/24/2015.
 */
public class InterfaceLiftDownloadHandler {

    private static final String TAG = "InterfaceLiftHandler";
    private static final String BASE_URL = "https://interfacelift.com/wallpaper/downloads/random/android/";


    public static String getLink() throws IOException {
        Log.d(TAG, "Getting link");
        String link = BASE_URL;
        URL url = new URL(link);
        HttpURLConnection ucon = (HttpURLConnection) url.openConnection();
        ucon.setRequestProperty("user-agent", "Mozilla");
        StringBuilder sb = new StringBuilder();
        try (
                InputStream is = ucon.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader((is)))) {
            String line = null;
            while (null != (line = br.readLine())) {
                sb.append(line);
            }
        }
        String imageLink = parseFirstImage(sb.toString());
        return imageLink;
    }

    private static String parseFirstImage(String html) {

        Log.d(TAG, "geting image file");
        Pattern p = Pattern.compile("href=\"(.*?)\"");
        Matcher m = p.matcher(html);
        String url = null;
        while (m.find()) {
            url = m.group(1); // this variable should contain the link URL
            if(url.endsWith(".jpg")) break;
        }
        Log.d(TAG, "Url is " + url);
        System.out.println("http://interfacelift.com" + url);
        return "http://interfacelift.com" + url;
    }

}
