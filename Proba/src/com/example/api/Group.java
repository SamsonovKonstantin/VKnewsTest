package com.example.api;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class Group implements Serializable {
    public long gid;
    public String name;
    public String photo;//50*50
   
    public static Group parseGroups(JSONObject jgroups) throws JSONException {
    	Group g=new Group();
        g.gid = jgroups.optLong("id");
        g.name = jgroups.optString("name");
        g.photo = jgroups.getString("photo_50");
        return g;
    }
}