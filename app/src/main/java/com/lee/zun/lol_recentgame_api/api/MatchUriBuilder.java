package com.lee.zun.lol_recentgame_api.api;

import android.net.Uri;

/**
 * Created by P400 on 2015-01-29.
 */




public class MatchUriBuilder extends BaseUriBuilder{
    private final String QUERY_API_VERSION = "v2.2";
    private final String QUERY_MATCH = "match";
    private final String QUERY_BY_SUMMONER = "by-summoner";
    private final String QUERY_RECENT = "recent";
    private long matchId = 0;



    public MatchUriBuilder matchId(long matchId) {
        this.matchId = matchId;
        return this;
    }

    /**
     * Set reigon id in path
     *
     * @param regionStr Reigon string (br, eune, enw, kr ... etc)
     * @return This class (Support method chaining)
     */
    @Override
    public MatchUriBuilder region(String regionStr) {
        super.region(regionStr);
        return this;
    }

    @Override
    public Uri build() {
        if (matchId == 0) {
            return null;
        }
        return getBuilder().build();
    }



    public Uri.Builder getBuilder(){
        return super.getBuilder()
                .appendPath(QUERY_API_VERSION)
                .appendPath(QUERY_MATCH)
                .appendPath(QUERY_BY_SUMMONER)
                .appendPath(String.valueOf(matchId))
                .appendPath(QUERY_RECENT)
                .appendQueryParameter(Config.PARAM_API_KEY, Config.API_KEY);
    }
}
