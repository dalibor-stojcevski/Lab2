package com.example.nastani;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
public class MainActivity extends Activity {
	private BroadcastReceiver mReceiver;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences pref = getSharedPreferences("MyPref", 0); 
        String sec="0";		
		pref.getString("DATUM", sec);
        long Pominato = Long.parseLong(sec);
        long Momentalno = System.currentTimeMillis();
		if((Momentalno-Pominato) > 24*60*60){
               startService(new Intent(this, Servis.class));
		       execute();
		}
        else {
        	execute();
        }    
    }
   public  void execute() {
	   PrikaziAsyn prikz = new PrikaziAsyn(this);
	   prikz.execute();	
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("DB_WRITE");
        	       
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
            	
                Log.d("BROADCAST","PRIMENO");
                //execute();  //blokira
            }			
        };
        this.registerReceiver(mReceiver, intentFilter);
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        this.unregisterReceiver(this.mReceiver);
    }  
}
