package com.example.api;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class User implements Serializable {
    public long uid;
    public String first_name;
    public String last_name;
    public String photo;
    public static User parseFromNews(JSONObject jprofile) throws JSONException {
        User m = new User();
        m.uid = jprofile.optLong("id");
        m.first_name = jprofile.optString("first_name");
        m.last_name = jprofile.optString("last_name");
        m.photo = jprofile.optString("photo_50");
        return m;
    }
    
}
