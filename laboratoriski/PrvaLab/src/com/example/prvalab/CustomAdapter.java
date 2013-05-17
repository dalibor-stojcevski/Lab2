package com.example.prvalab;

import java.util.ArrayList;
import com.example.prvalab.R;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
	ArrayList<Data> niza;
	private Context context;
	 
    public CustomAdapter(Context context ) {
        niza = new ArrayList<Data>();

        niza.add(new Data("Albert Ainstain",Integer.toString(R.drawable.albert)));
        niza.add(new Data("Nikola Tesla",Integer.toString(R.drawable.nikola)));
        this.context = context;  
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return niza.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method 
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView  = inflater.inflate(R.layout.listitem, parent, false);
        }
		TextView textView = (TextView) convertView.findViewById(R.id.label);
        textView.setText(niza.get(position).getName());
        
        Button button = (Button) convertView.findViewById(R.id.button);
        button.setText(R.string.button_view);
        
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewPicture.class); 
                String image = niza.get(position).getImage();
                intent.putExtra("IMAGE", image);
                context.startActivity(intent);
            }
        });
		return convertView;
	}

}
