package com.example.linxj.gson;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Model.BookInfo;
import Model.Content;
import Model.Easy;

/**
 * Created by linxj on 2015/9/26.
 */
public class MyJsonParse {
    public MyJsonParse(){}
    public static String parse(String json){
        Gson gson = new Gson();
        JsonElement element = new JsonParser().parse(json);
        JsonObject object = element.getAsJsonObject();
        JsonElement cElement = object.get("summary");
        Content c = gson.fromJson(cElement, Content.class);
        return c.get$t();
    }
    public static BookInfo parseInfoBook(String json){
        Gson gson = new Gson();
        BookInfo info = gson.fromJson(json, BookInfo.class);
        //String c = info.getContent().getContent();
        return info;
    }
    public static String easyParse(){
        //Easy easy = new Easy();
        return "{name:\"mike\",age:18}";
    }
    public static String arrayParse(){
        //Easy easy = new Easy();
        return "[{name:\"mike\",age:11},{name:\"alan\",age:15}]";
    }
}
