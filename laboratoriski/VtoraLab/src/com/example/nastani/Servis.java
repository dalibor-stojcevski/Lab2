package com.example.nastani;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class Servis extends Service {

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		 AsynNastani asn = new AsynNastani(this);
		 asn.execute();
		 return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
