package com.lee.zun.lol_recentgame_api.api;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Namhyun on 2015-01-16.
 */
public class SummonerUriBuilder extends BaseUriBuilder {
    private final String QUERY_API_VERSION = "v1.4";
    private final String QUERY_SUMMONER = "summoner";
    private final String QUERY_BY_NAME = "by-name";

    private final String QUERY_MASTERIES = "masteries";
    private final String QUERY_NAME = "name";
    private final String QUERY_RUNES = "runes";

    private String summonerName = null;
    private String summonerIds = null;

    private boolean usesByName = false;
    private boolean requestMasteries = false;
    private boolean requestName = false;
    private boolean requestRunes = false;

    /**
     * Set summoner id in path
     *
     * @param summonerIds Summoner id
     * @return This class
     */
    public SummonerUriBuilder summonerIds(String summonerIds) {
        this.summonerIds = summonerIds;
        return this;
    }

    /**
     * Set summoner name in path
     *
     * @param summonerName Summoner name
     * @return
     */
    public SummonerUriBuilder summonerName(String summonerName) {
        this.summonerName = summonerName;
        return this;
    }

    /**
     * Set by name in path (uses summoner name)
     *
     * @return This class
     */
    public SummonerUriBuilder byName() {
        this.usesByName = true;
        return this;
    }

    /**
     * Set request masteries in path (uses summoner id)
     *
     * @return This class
     */
    public SummonerUriBuilder requestMasteries() {
        this.requestMasteries = true;
        return this;
    }

    /**
     * Set request name in path (uses summoner id)
     *
     * @return This class
     */
    public SummonerUriBuilder requestName() {
        this.requestName = true;
        return this;
    }

    /**
     * Set request runes in path (uses summoner id)
     *
     * @return This class
     */
    public SummonerUriBuilder requestRunes() {
        this.requestRunes = true;
        return this;
    }

    /**
     * Set reigon id in path
     *
     * @param reigonStr Reigon string (br, eune, enw, kr ... etc)
     * @return This class (Support method chaining)
     */
    @Override
    public SummonerUriBuilder reigon(String reigonStr) {
        super.reigon(reigonStr);
        return this;
    }

    @Override
    public Uri build() {
        if (usesByName && summonerName == null) {
            return null;
        } else if (summonerIds == null && !usesByName) {
            return null;
        }
        return getBuilder().build();
    }

    @Override
    public Uri.Builder getBuilder() {
        Uri.Builder builder = super.getBuilder()
                .appendPath(QUERY_API_VERSION)
                .appendPath(QUERY_SUMMONER);
        if(usesByName){
            builder.appendPath(QUERY_BY_NAME)
                   .appendPath(summonerName);
        } else{
            builder.appendPath(summonerIds);
            if(requestMasteries)
                builder.appendPath(QUERY_MASTERIES);
            else if(requestName)
                builder.appendPath(QUERY_NAME);
            else if(requestRunes)
                builder.appendPath(QUERY_RUNES);
        }
        builder.appendQueryParameter(Config.PARAM_API_KEY, Config.API_KEY);
        return builder;
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
