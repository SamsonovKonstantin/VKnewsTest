package com.example.api;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

import com.example.proba.ListNews;

public class JSONAsynkTask extends AsyncTask<String, Void, JSONObject>{

	private ListNews _activity;
	JSONObject root;

	public JSONAsynkTask(ListNews activity) {
		super();
		this._activity = activity;
	}

	@Override
	protected JSONObject doInBackground(String... params) {
		JSONObject root = null;

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost postMethod = new HttpPost(params[0]);
		try {
			HttpResponse httpResponse = httpclient.execute(postMethod);
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				String entityStr = EntityUtils.toString(httpEntity);
				
				JSONObject jsonObject = new JSONObject(entityStr);
				root = jsonObject.getJSONObject("response");
			}

		} catch (JSONException e) {
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
		return root;
	}

	protected void onPostExecute(JSONObject result) {
		try {
			_activity.resultCon(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
}
