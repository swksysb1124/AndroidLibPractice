package com.jasonstudio.jy.androidlib.http.url;

import android.support.annotation.NonNull;

public interface URLConfigManager {
	@NonNull URLInfo findURL(String findKey);
}
