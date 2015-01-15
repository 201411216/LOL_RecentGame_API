package com.lee.zun.lol_recentgame_api.api;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Namhyun, Gu on 2015-01-15.
 */
public class BaseUriBuilder {
    private String reigonStr = null;

    /**
     * Set reigon in path
     *
     * @param reigonStr Reigon string (br, eune, enw, kr ... etc)
     * @return This class (Support method chaining)
     */
    public BaseUriBuilder reigon(String reigonStr) {
        this.reigonStr = reigonStr;
        return this;
    }

    /**
     * Built uri
     *
     * @return Uri
     */
    public Uri build() {
        Uri.Builder builder = getBuilder();
        if (builder == null) {
            return null;
        }
        return builder.build();
    }

    /**
     * Create Uri.Builder by param value
     *
     * @return Uri.Builder
     */
    public Uri.Builder getBuilder() {
        if (reigonStr == null) {
            return null;
        }
        return Uri.parse(String.format(Config.BASE_URL, reigonStr, reigonStr)).buildUpon();
    }

    /**
     * Create URL of Uri
     *
     * @return URL
     * @throws MalformedURLException
     */
    public URL getURL() throws MalformedURLException {
        Uri uri = build();
        if (uri == null) {
            return null;
        }
        return new URL(uri.toString());
    }
}