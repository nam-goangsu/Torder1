package org.teechtown.testtanb;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerContect extends Thread {

    StringBuilder Out = new StringBuilder();
    String result = null;
    int resCode = 0;
    BufferedReader reader = null;
    InputStreamReader  inputStream = null;
    HttpURLConnection conn = null;
    private String Json;
    private String Mockupurl1;
    public String url = "https://gist.githubusercontent.com/torder-dev/6736a8e0459d3f8e234038af45608f25/raw/2fefdc4b4a587e5c9b66bafe9048f50afa4d6315/mock.json";

    public ServerContect(String url){
        url = url;
    }
    @Override
    public void run() {
        final String output = Mockup(url);
        result = output;
    }
    public String getResult(){
        return result;
    }

    public String Mockup(String Mockupurl1) {
        StringBuilder output = new StringBuilder();
        try {
            java.net.URL url = new URL(Mockupurl1);
            conn = (HttpURLConnection) url.openConnection();
            if (conn != null) {
                conn.setRequestMethod("GET");

                InputStream is = conn.getInputStream();


                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                result = null;

                while(true) {
                    result = reader.readLine().trim();
                    if (result == null) {
                        break;
                    }
                    output.append(result);
                }

                reader.close();
                conn.disconnect();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return output.toString();
    }

}
