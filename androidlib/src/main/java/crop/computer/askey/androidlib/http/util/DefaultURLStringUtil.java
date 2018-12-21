package crop.computer.askey.androidlib.http.util;

import crop.computer.askey.androidlib.http.url.URLInfo;

public class DefaultURLStringUtil {
	
	public static String toString(URLInfo urlData) {
		StringBuilder builder = new StringBuilder();
		builder.append(urlData.getScheme());
		builder.append(urlData.getHost());
		builder.append(urlData.getPath());
		return builder.toString();
	}
}
