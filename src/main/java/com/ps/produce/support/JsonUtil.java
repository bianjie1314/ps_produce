package com.ps.produce.support;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.List;

import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
    private static Gson googleJson = null;
    public synchronized static Gson getGson(){
        if(googleJson == null) {
            GsonBuilder builder = new GsonBuilder();
//            builder.setDateFormat(DateFormat.LONG);
//            builder.addSerializationExclusionStrategy(new IgnoreStrategy());
            builder.registerTypeAdapter(java.util.Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
            builder.registerTypeAdapter(java.util.Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);
            googleJson = builder.create();
        }
        return googleJson;
    }

    /**
     * List<T> 转 json 保存到数据库
     */
    public static <T> String toJson(List<T> ts) {
        String urlsJson = getGson().toJson(ts);
        return urlsJson;
    }
    /**
     * List<T> 转 json 保存到数据库
     */
    public static <T> String toJson(T ts) {
        String urlsJson = getGson().toJson(ts);
        return urlsJson;
    }

    /**
     * json 转 List<T>
     */
    public static Object toObject(String jsonString, Type type) {
        return getGson().fromJson(jsonString, type);
    }
    /**
     * json 转 List<T>
     */
    public static <T> T  toObject(String jsonString, Class<T> classOfT) {
        return getGson().fromJson(jsonString, classOfT);
    }

}
