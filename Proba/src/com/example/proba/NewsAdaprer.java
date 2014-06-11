package com.example.proba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NewsAdaprer extends BaseAdapter {
	Context ctx;
	  LayoutInflater lInflater;
	  public ListNews newsfeed ;

	 NewsAdaprer(Context context,ListNews newsfeed){
		
		 ctx = context;
		 this.newsfeed = newsfeed;
		    lInflater = (LayoutInflater) ctx
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		if(newsfeed != null && newsfeed.items != null){
			return newsfeed.items.size();
			}else{
			return 0;
			}
	}

	public Object getItem(int position) {
		return newsfeed.items.get(position).text;
	}

	public long getItemId(int position) {
		return newsfeed.items.get(position).post_id;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
	   View view = convertView;
	   view = lInflater.inflate(R.layout.news, null);
//	   if(newsfeed.items.get(position).source_id > 0){
//		   newsfeed.profiles.get(position).first_name;
//		   ((TextView) view.findViewById(R.id.name)).setText(newsfeed.profiles.get(position).first_name +"  "+newsfeed.profiles.get(position).last_name);
//	   }else{
//		   ((TextView) view.findViewById(R.id.name)).setText(newsfeed.groups.get(position).name);
//	   }
	   ((TextView) view.findViewById(R.id.news)).setMaxLines(2);
	   ((TextView) view.findViewById(R.id.news)).setText(newsfeed.items.get(position).text);
//	   ((ImageView) view.findViewById(R.id.photo)).setImageBitmap(newsfeed.profiles.get(position).photo);
	   return view;
	}
}
