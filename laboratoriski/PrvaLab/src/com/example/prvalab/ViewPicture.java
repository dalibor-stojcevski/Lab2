package com.example.prvalab;

import com.example.prvalab.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewPicture extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
	    String image = intent.getStringExtra("IMAGE");
	    int resId = Integer.parseInt(image);

	    // Create the text view
	    ImageView imageView = new ImageView(this);

	   // textView.setText(message);
         imageView.setImageResource(resId);
	    // Set the text view as the activity layout
	    setContentView(imageView);


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_picture, menu);
		return true;
	}

}
