package com.lee.zun.lol_recentgame_api.api;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Namhyun, Gu on 2015-01-15.
 */
public class GameUriBuilder extends BaseUriBuilder {
    private final String QUERY_API_VERSION = "v1.3";
    private final String QUERY_GAME = "game";
    private final String QUERY_BY_SUMMONER = "by-summoner";
    private final String QUERY_RECENT = "recent";
    private long summonerId = 0;

    /**
     * Set summoner id in path
     *
     * @param summonerId Summoner id
     * @return This class (Support method chaining)
     */
    public GameUriBuilder summonerId(long summonerId) {
        this.summonerId = summonerId;
        return this;
    }

    /**
     * Set reigon id in path
     *
     * @param reigonStr Reigon string (br, eune, enw, kr ... etc)
     * @return This class (Support method chaining)
     */
    @Override
    public GameUriBuilder reigon(String reigonStr) {
        super.reigon(reigonStr);
        return this;
    }

    @Override
    public Uri build() {
        if (summonerId == 0) {
            return null;
        }
        return getBuilder().build();
    }

    @Override
    public Uri.Builder getBuilder() {
        return super.getBuilder()
                .appendPath(QUERY_API_VERSION)
                .appendPath(QUERY_GAME)
                .appendPath(QUERY_BY_SUMMONER)
                .appendPath(String.valueOf(summonerId))
                .appendPath(QUERY_RECENT)
                .appendQueryParameter(Config.PARAM_API_KEY, Config.API_KEY);
    }

    @Override
    public URL getURL() throws MalformedURLException {
        Uri uri = build();
        if (uri == null) {
            return null;
        }
        return new URL(uri.toString());
    }
}
