package com.lee.zun.lol_recentgame_api;

import android.net.Uri;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2015-01-13.
 */

//TODO serch action prvider


public class RecentGameFragment {

    public java.net.URL getSummonerIDURL() {

        String _REGION = "kr";
        String LOL_VERSION = "v1.4";
        String SUMMONER = "summoner";
        String BY_NAME = "by-name";
        String USERNAME = "The Zun";
        String API_KEY = "dba2bb91-3fa9-4c56-8bc7-976deed149fe";
        Uri buildSummonerIDUri = Uri.parse("https://kr.api.pvp.net/api/lol").buildUpon()
                .appendPath(_REGION)
                .appendPath(LOL_VERSION)
                .appendPath(SUMMONER)
                .appendPath(BY_NAME)
                .appendPath(USERNAME)
                .appendQueryParameter("api-key", API_KEY).build();

        URL summonerIDURL = null;

        try {
            summonerIDURL = new URL(buildSummonerIDUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return summonerIDURL;

    }

    public int getSummonerUserID(String idJSONStr) {

        int resultID = 0;

        try {
            JSONObject idJSONObject = new JSONObject(idJSONStr);
            JSONArray idJSONARRAY = idJSONObject.getJSONArray("The Zun");
            for (int i = 0; i < idJSONARRAY.length(); i++) {
                JSONObject resultObject = idJSONARRAY.getJSONObject(i);
                resultID = resultObject.getInt("id");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return resultID;
        }

    }

    public java.net.URL getRecentGameURL(int _id) {

        String _REGION = "kr";
        String LOL_VERSION = "v1.3";
        String _GAME = "game";
        String _BYSUMMONER = "by-summoner";
        String _ID = String.valueOf(_id);
        String API_KEY = "dba2bb91-3fa9-4c56-8bc7-976deed149fe";

        Uri buildRecentGameUri = Uri.parse("https://kr.api.pvp.net/api/lol").buildUpon()
                .appendPath(_REGION)
                .appendPath(LOL_VERSION)
                .appendPath(_GAME)
                .appendPath(_BYSUMMONER)
                .appendPath(_ID)
                .appendQueryParameter("api_key", API_KEY).build();

        URL recentGameURL = null;

        try {
            recentGameURL = new URL(buildRecentGameUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return recentGameURL;

    }

    public List<RecentGameContainer> getRecentGameInfo(String gameInfoJSONStr) {
        List<RecentGameContainer> recentGameList = new ArrayList<>();
        String summonerIdURL = getSummonerIDURL().toString();
        int summonerID = getSummonerUserID(summonerIdURL);
        URL recentGameURL = getRecentGameURL(summonerID);

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(recentGameURL)
                .build();

        Gson recentGameInfo = new Gson();
        return null;
    }
}