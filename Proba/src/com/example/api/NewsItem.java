package com.example.api;

import org.json.JSONException;
import org.json.JSONObject;

public class NewsItem {
    public long source_id,post_id;
    public String text;
    
 public static NewsItem parse(JSONObject jitem) throws JSONException {
        NewsItem newsitem = new NewsItem();
        newsitem.source_id = jitem.optLong("source_id");
        newsitem.text = jitem.optString("text");
        newsitem.post_id = jitem.optInt("post_id");
        return newsitem;
    }
}
