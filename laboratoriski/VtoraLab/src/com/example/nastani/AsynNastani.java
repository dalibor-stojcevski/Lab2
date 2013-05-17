package com.example.nastani;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

public class AsynNastani extends  AsyncTask<String, Integer, String>{
	private Context context;
	public AsynNastani(Context context) {
		super();
		this.context = context;
	}
	protected String doInBackground(String... arg0) {
		try {
            String result = GetNastani.connect("http://ws.audioscrobbler.com/2.0/?method=geo.getevents&location=macedonia&api_key=8af293b8cdae989bfdd9907e652f1083&format=json");
            return result;
        } catch (Exception e) {
            return new String();
        }
	}
	protected void onPostExecute(String result) {
	    	
	    	ArrayList<NastaniData> nastanidata = new ArrayList<NastaniData>();
	        if (result.length() == 0) {
	            return;
	        }
	        try {
				JSONObject respObj = new JSONObject(result);
				JSONObject topTracksObj = respObj.getJSONObject("events");
				JSONArray events = topTracksObj.getJSONArray("event");
				for(int i=0; i<events.length(); i++) {
					JSONObject event = events.getJSONObject(i);	
					String title = event.getString("title");
					String opis = event.getString("description");
					JSONObject artistObj = event.getJSONObject("artists");
					String headliner = artistObj.getString("headliner");
					JSONObject venueObj = event.getJSONObject("venue");
					String venueName = venueObj.getString("name");
					JSONObject locationObj = venueObj.getJSONObject("location");
					String city =  locationObj.getString("city");
					JSONArray imageUrls = event.getJSONArray("image");
					String imageUrl = null;
					for(int j=0; j<imageUrls.length(); j++) {
						JSONObject imageObj = imageUrls.getJSONObject(j);
						imageUrl = imageObj.getString("#text");
						if(imageObj.getString("size").equals("medium")) {
							break;
						}
					}
					nastanidata.add(new NastaniData(title,headliner,imageUrl, city, venueName, opis));
					baza baz = new baza(this.context);
					try{
						 baz.addNastan(new NastaniData(title,headliner,imageUrl, city, venueName, opis));
					}
					catch (Exception e) {
							// TODO Auto-generated catch block
							Log.e("BAZA GRESkA",e.toString());
					}
					long  seconds = System.currentTimeMillis();
					SharedPreferences pref = context.getSharedPreferences("MyPref", 0); 
					pref.edit().putString("DATUM",Long.toString(seconds)).commit();
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Intent intent = new Intent();
			intent.setAction("DB_WRITE");
			context.sendBroadcast(intent);    
	    }
}
