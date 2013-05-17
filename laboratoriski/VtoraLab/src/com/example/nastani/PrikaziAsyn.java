package com.example.nastani;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PrikaziAsyn extends AsyncTask<String, Integer, String> {
	private MainActivity activity;
	private Context context;
	private ListView lv;
	ArrayList<String> niza = new ArrayList<String>();
	List<NastaniData> nastanList = new ArrayList<NastaniData>();
	public PrikaziAsyn(MainActivity activity) {
		super();
		this.activity = activity;
		this.context = this.activity.getApplicationContext();
	}
	protected  String doInBackground(String... params) {
		// TODO Auto-generated method stub
		 lv = (ListView) this.activity.findViewById(R.id.lista);
		 baza baz = new baza(this.activity);
		 nastanList = baz.getAllContacts();
		 for (NastaniData cn : nastanList) {
	            String title = cn.getTitle();
	            niza.add(title);
	            Log.d("TITLE=",title);
	        
	     }
		 ArrayAdapter<String> arrayAdapter =      
		            new ArrayAdapter<String>(this.activity,R.layout.listitem, niza);
		 lv.setAdapter(arrayAdapter); 
		 return null;	
	}
	 protected  List<NastaniData> onPostExecute() 
	    {
		 return null;		 
	}
}
