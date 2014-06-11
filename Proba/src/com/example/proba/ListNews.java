package com.example.proba;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.api.Group;
import com.example.api.JSONAsynkTask;
import com.example.api.NewsItem;
import com.example.api.User;

public class ListNews extends ActionBarActivity implements OnItemClickListener {

	String user_id, access_token;
	ListView lvMain;
	Button refresh,logout;
	NewsAdaprer adapter;
	JSONObject jSONObject;
	
	public ArrayList<NewsItem> items;
    public ArrayList<User> profiles;
    public ArrayList<Group> groups;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_news);
		
		Intent intent = getIntent();
		user_id = intent.getStringExtra("user_id");
		access_token = intent.getStringExtra("access_token");
		
		logout=(Button)findViewById(R.id.logout);
		refresh=(Button)findViewById(R.id.refresh);
		lvMain=(ListView)findViewById(R.id.lvMain);
		lvMain.setOnItemClickListener(this);
		
		con();
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
	
	public ListNews resultCon(JSONObject result) throws JSONException {

		JSONArray jitems = result.getJSONArray("items");
		JSONArray jprofiles = result.getJSONArray("profiles");
        JSONArray jgroups = result.getJSONArray("groups");
        ListNews newsfeed = new ListNews();
        if (jitems != null) {
            newsfeed.items = new ArrayList<NewsItem>();
            for(int i = 0; i < jitems.length(); i++) {
                JSONObject jitem = (JSONObject)jitems.get(i);
                NewsItem newsitem = NewsItem.parse(jitem);
                newsfeed.items.add(newsitem);
            }
        }
        
        if (jprofiles != null) {
            newsfeed.profiles = new ArrayList<User>();
            for(int i = 0; i < jprofiles.length(); i++) {
                JSONObject jprofile = (JSONObject)jprofiles.get(i);
                User m = User.parseFromNews(jprofile);
                newsfeed.profiles.add(m);
            }
        }
        if (jgroups != null)
            newsfeed.groups = new ArrayList<Group>();
        	for(int i = 0; i < jgroups.length(); i++) {
        	JSONObject jgroup = (JSONObject)jgroups.get(i);
        	Group g = Group.parseGroups(jgroup);
        	newsfeed.groups.add(g);
        	}
        	
        NewsAdaprer adapter = new NewsAdaprer(this, newsfeed);
		lvMain.setAdapter(adapter);
		return newsfeed;
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
	
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		String text = parent.getItemAtPosition(position).toString();
		next(text);
	}
	
	
	public boolean next(String text) {
		
		Intent intent = new Intent();
		intent.putExtra("access_token", access_token);
		intent.putExtra("post",text);
		intent.setClass(getApplicationContext(), News.class);
		startActivity(intent);
		return true;
	}

}
