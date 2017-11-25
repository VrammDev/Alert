package com.example.toshiba.Conexion;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class conexion_webservice {

    public static JSONObject getData (String uri){
        HttpURLConnection connection=null;
        BufferedReader reader=null;
        try {
            URL url = new URL(uri);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(10000);
            connection.setDoOutput(true);
            connection.connect();
            InputStream inputStream = null;
            try {
                inputStream = connection.getInputStream();
            } catch(FileNotFoundException e) {
                inputStream = connection.getErrorStream();
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer buffer=new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            JSONObject obj=new JSONObject(buffer.toString());
            return  obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }}}

}
