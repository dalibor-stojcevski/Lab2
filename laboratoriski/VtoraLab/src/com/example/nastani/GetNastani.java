package com.example.nastani;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class GetNastani {
	public String result="";
	public static String connect(String url)
	{
        String rezultat = "prazno";
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpGet httpget = new HttpGet(url); 
	    HttpResponse response;
	    try {
	        response = httpclient.execute(httpget);
	        HttpEntity entity = response.getEntity();
	        if (entity != null) {
	            InputStream instream = entity.getContent();
	            rezultat = convertStreamToString(instream);
	            instream.close(); 
	        } 
	    } catch (Exception e) {
	    	Log.e("HTTPEror",e.toString());
	    }
	    return rezultat;
	}

	    private static String convertStreamToString(InputStream is) {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    try {
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            is.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return sb.toString();
	}

}
