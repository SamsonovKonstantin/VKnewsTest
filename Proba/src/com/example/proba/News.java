package com.example.proba;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class News extends ActionBarActivity{
	
	TextView textView1;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post);
		Intent intent = getIntent();
//		String access_token = intent.getStringExtra("access_token");
		String text= intent.getStringExtra("post");
		
		textView1=(TextView)findViewById(R.id.textView1);
		textView1.setText(text);
		textView1.setTextSize(20);
		
	}
	
}
