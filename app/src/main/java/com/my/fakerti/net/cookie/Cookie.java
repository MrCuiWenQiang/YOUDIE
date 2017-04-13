package com.my.fakerti.net.cookie;

import java.util.HashMap;
import java.util.List;

import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Cookie持久化类，可以将cookie保存到本地.
 * Created by Mr.C on 2017/3/31 0031.
 */

public class Cookie implements CookieJar {

    private final HashMap<String,List<okhttp3.Cookie>> cookieStore = new HashMap<String, List<okhttp3.Cookie>>();
    @Override
    public void saveFromResponse(HttpUrl url, List<okhttp3.Cookie> cookies) {
        cookieStore.put(url.host(),cookies);
    }

    @Override
    public List<okhttp3.Cookie> loadForRequest(HttpUrl url) {
        return cookieStore.get(url);
    }
}
