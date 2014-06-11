package com.example.proba;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.api.JSONAsynkTask;

public class ListNews extends ActionBarActivity{
	
	String user_id, access_token,text;
	ListView lvMain;
	Button refresh,logout;
	ArrayAdapter<String> adapter;
	JSONObject jSONObject;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_news);
		
		Intent intent = getIntent();
		user_id = intent.getStringExtra("user_id");
		access_token = intent.getStringExtra("access_token");
		
		logout=(Button)findViewById(R.id.logout);
		refresh=(Button)findViewById(R.id.refresh);
		lvMain=(ListView)findViewById(R.id.lvMain);
		
		con();
	}
	
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		next(id);
	}
	
	public String url(Object... par) {
		return "https://api.vk.com/method/newsfeed.get?uid="+user_id+"&filters=post&access_token=" + access_token + "&v=5.21";
	}
	
	public void con() {
		JSONAsynkTask con = new JSONAsynkTask(this);
		try {
			jSONObject = con.execute(url()).get();
		} catch (InterruptedException e) {
			Log.e("log123", "InterruptedException = " + e);
		} catch (ExecutionException e) {
			Log.e("log123", "ExecutionException = " + e);
		}
	}
	
	public void resultCon(JSONObject result) throws JSONException {

		ArrayList<String> items = new ArrayList<String>();
		JSONArray itemsArray = result.getJSONArray("items");
		for (int i = 0; i < itemsArray.length(); i++) {
			JSONObject jitems = itemsArray.getJSONObject(i);
			text = jitems.getString("text");
			String n = new String(text);
			items.add(n);

		}
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);

		lvMain.setAdapter(adapter);

	}
	
	public void logout(View v) {
    	CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        access_token=null;
        user_id=null;
        finish();
	}
	
	public void refresh(View v){
    	lvMain.smoothScrollToPosition(0);
		con();
	}

	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
	
	public boolean next(Object... par) {

		Intent intent = new Intent();
		intent.putExtra("user_id", user_id);
		intent.putExtra("access_token", access_token);
		intent.putExtra("aid",(Long) par[0]);
		intent.setClass(getApplicationContext(), News.class);
		startActivity(intent);
		return true;

	}
}
