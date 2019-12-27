package com.jasonstudio.jy.androidlib.http.request;

import java.util.Locale;

public class HeaderField {
	public String key;
	public String value;
	
	public HeaderField(String key, String value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public String toString() {
		return String.format(Locale.US, "%s: %s", key, value);
	}
}
