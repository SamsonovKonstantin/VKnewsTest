package com.example.api;

public class Auth {
    
    public static String redirect_url="https://oauth.vk.com/blank.html";
    public static String getUrl(String api_id, String settings){
        String url="https://oauth.vk.com/authorize?client_id="+api_id+"&display=mobile&scope="+settings+"&redirect_uri="+redirect_url+"&response_type=token"
                +"&v=5.21";
        return url;
    }
    
    public static String getSettings(){
        return "friends,wall"; //,groups,messages,offline,notifications,notify,photos,audio,video,docs,status,notes,pages";
    }
    
    public static String[] parseRedirectUrl(String url) throws Exception {
        String access_token=VKUtil.extractPattern(url, "access_token=(.*?)&");
        String user_id=VKUtil.extractPattern(url, "user_id=(\\d*)");
        if(user_id==null || user_id.length()==0 || access_token==null || access_token.length()==0)
            throw new Exception("Failed to parse redirect url "+url);
        return new String[]{access_token, user_id};
    }
}