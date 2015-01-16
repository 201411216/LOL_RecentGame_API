package com.lee.zun.lol_recentgame_api;

import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Log;

import com.lee.zun.lol_recentgame_api.api.Config;
import com.lee.zun.lol_recentgame_api.api.GameUriBuilder;
import com.lee.zun.lol_recentgame_api.api.SummonerUriBuilder;

/**
 * Created by Namhyun, Gu on 2015-01-16.
 */
public class ApiTest extends AndroidTestCase {
    private String LOG_TAG = this.getClass().getSimpleName();

    /**
     * Get uri example
     */
    public void testGetUri(){
        String expectedValue1
                = "https://kr.api.pvp.net/api/lol/kr/v1.3/game/by-summoner/100/recent?api_key=" + Config.API_KEY;

        String expectedValue2
                = "https://kr.api.pvp.net/api/lol/kr/v1.4/summoner/by-name/test?api_key=" + Config.API_KEY;

        String expectedValue3
                = "https://kr.api.pvp.net/api/lol/kr/v1.4/summoner/100?api_key=" + Config.API_KEY;

        String expectedValue4
                = "https://kr.api.pvp.net/api/lol/kr/v1.4/summoner/100/masteries?api_key=" + Config.API_KEY;

        String expectedValue5
                = "https://kr.api.pvp.net/api/lol/kr/v1.4/summoner/100/name?api_key=" + Config.API_KEY;

        String expectedValue6
                = "https://kr.api.pvp.net/api/lol/kr/v1.4/summoner/100/runes?api_key=" + Config.API_KEY;

        Uri uri = new GameUriBuilder().reigon("kr").summonerId(100).build();
        Log.d(LOG_TAG, uri.toString());
        assertEquals(expectedValue1, uri.toString());

        uri = new SummonerUriBuilder().reigon("kr").byName().summonerName("test").build();
        Log.d(LOG_TAG, uri.toString());
        assertEquals(expectedValue2, uri.toString());

        uri = new SummonerUriBuilder().reigon("kr").summonerIds("100").build();
        Log.d(LOG_TAG, uri.toString());
        assertEquals(expectedValue3, uri.toString());

        uri = new SummonerUriBuilder().reigon("kr").summonerIds("100").requestMasteries().build();
        Log.d(LOG_TAG, uri.toString());
        assertEquals(expectedValue4, uri.toString());

        uri = new SummonerUriBuilder().reigon("kr").summonerIds("100").requestName().build();
        Log.d(LOG_TAG, uri.toString());
        assertEquals(expectedValue5, uri.toString());

        uri = new SummonerUriBuilder().reigon("kr").summonerIds("100").requestRunes().build();
        Log.d(LOG_TAG, uri.toString());
        assertEquals(expectedValue6, uri.toString());
    }
}
