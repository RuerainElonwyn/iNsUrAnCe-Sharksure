package com.sharkfintech.insurancesharksure;

import android.os.AsyncTask;

import org.apache.http.UnsupportedHttpVersionException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Orcas on 29/7/2016.
 */
public class DataUploader {
    private static final String UPLOAD_URL = "http://sharksure-kashie.rhccloud.com/"; //
    private JSONArray data_array;
    private String user = "chandrasekaranakash2000@gmail.com";
    public DataUploader(String user, String type, String raw_data){
        String[] data = raw_data.split(",");
        data_array = new JSONArray();
        this.user = user;
        for(int i = 0; i < data.length; i += 2){
            String time = data[i];
            String value = data[i+1];
            JSONObject json = new JSONObject();
            try{
                json.put("time", time);
                json.put("type", type);
                json.put("value", value);
                data_array.put(json);
            }catch(JSONException e){
                e.printStackTrace();
                continue;
            }
        }
    }
    public void post(){
        try{
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost request = new HttpPost(UPLOAD_URL + user);
            StringEntity se = new StringEntity(data_array.toString());
            request.setEntity(se);
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");
            HttpResponse response = httpClient.execute(request);
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
