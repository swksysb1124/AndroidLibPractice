package com.jasonstudio.jy.androidlib.util;

import com.google.gson.Gson;

public class JSONParserUtil {
	private static final Gson gson = new Gson();
	
	public static String toJson(Object o) {
		return gson.toJson(o);
	}
	
	public static <T> T fromJson(String json, Class<T> classOfT){
		return gson.fromJson(json, classOfT);
	}
}
