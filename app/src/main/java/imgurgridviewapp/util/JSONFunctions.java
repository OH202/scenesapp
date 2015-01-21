package imgurgridviewapp.util;


import org.apache.http.HttpEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Owner on 11/1/2014.
 */
public class JSONFunctions {

    private static String CLIENT_ID;
    private static String CLIENT_SECRET;
    private static String USERNAME;

    public static JSONObject getJSONfromURL(String url){

        InputStream is = null;
        String result = "";
        JSONObject jArray = null;
        HttpEntity entity = null;

        String CLIENT_ID = "fbf48c8a78af012";
        String CLIENT_SECRET = "a3f60a58a68b403e4b22ab081aba3bcf9858b81f";
        String USERNAME = "oh2o2";

        /*try {

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            httpPost.setHeader("Authorization", "Client-ID " + CLIENT_ID);

            HttpResponse response = httpClient.execute(httpPost);
            entity = response.getEntity();

            is = entity.getContent();


        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Log.e("log_tag", "Error in HTTP Connection " + e.toString());
        }

        try {



            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {

                sb.append(line + "\n");

            }

            is.close();

            result = sb.toString();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Log.e("log_tag", "Error converting result " + e.toString());
        }

        try {

            jArray = new JSONObject(result);

        } catch (JSONException e) {
            Log.e("log_tag", "Error parsing data " + e.toString());
        }

        System.out.println("JSON parsed: " + result);
        return jArray;

        */

        URL imgURL = null;
        try {
            imgURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) imgURL.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            conn.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        conn.setRequestProperty("Authorization", "Client-ID " + CLIENT_ID);

        BufferedReader bin = null;

        StringBuilder sb = new StringBuilder();

        try {
            bin = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String line = null;
        try {
            while ((line = bin.readLine()) != null) {

                sb.append(line + "\n");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        result = sb.toString();

        try {
            jArray = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("JSON parsed: " + result);
        return jArray;

    }

}



