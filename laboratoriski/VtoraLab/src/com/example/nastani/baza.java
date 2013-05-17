package com.example.nastani;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class baza extends SQLiteOpenHelper {
	
	 
	private static final String KEY_ID = "_id";
	private static final String TABLE_NASTANI = "NASTANI";
	private static final String KEY_TITLE = "TITLE";
	private static final String KEY_IMAGEURL = "IMAGEURL";
	private static final String KEY_VENUENAME = "VENUENAME";
	private static final String KEY_OPIS = "OPIS";
	private static final String KEY_CITY = "CITY";
	private static final String KEY_HeadLINER = "HeadLINER";
	private static final String nastaniBaza = "nastanibaza";

	public baza(Context context) {
	        super(context, nastaniBaza, null, 1);
	    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		 String CREATE_NASTANI_TABLE = "CREATE TABLE " + TABLE_NASTANI + "("
	                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
	                 + KEY_IMAGEURL + " TEXT," + KEY_CITY + " TEXT,"
	                 + KEY_VENUENAME + " TEXT," + KEY_OPIS + " TEXT,"
	                + KEY_HeadLINER + " TEXT" + ")";
	     
			db.execSQL(CREATE_NASTANI_TABLE);
	 }
		
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NASTANI);
        onCreate(db);
		
	}
	public void addNastan(NastaniData nastan) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("Zapisuva",nastan.getTitle());
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, nastan.getTitle()); 
        values.put(KEY_IMAGEURL, nastan.getImageUrl()); 
        values.put(KEY_CITY, nastan.getCity()); 
        values.put(KEY_VENUENAME, nastan.getvenueName()); 
        values.put(KEY_HeadLINER, nastan.getheadliner());
       try{ db.insert(TABLE_NASTANI, null, values);
       }
       catch(Exception e){
    	   Log.e("baza",e.toString());
       }
        db.close(); 
    }
 
	public List<NastaniData> getAllContacts() {
	    List<NastaniData> nastanList = new ArrayList<NastaniData>();
	    String selectQuery = "SELECT  * FROM " + TABLE_NASTANI;
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	        do {
	            NastaniData nastan= new NastaniData(cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4), cursor.getString(5), cursor.getString(6));
	            nastanList.add(nastan);
	        } while (cursor.moveToNext());
	    }
	    return nastanList;
	}
}
