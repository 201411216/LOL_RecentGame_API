package com.lee.zun.lol_recentgame_api;

import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Log;

import com.lee.zun.lol_recentgame_api.api.Config;
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

        Uri uri = new SummonerUriBuilder().region("kr").byName().summonerName("test").build();
        Log.d("ApiTest", uri.toString());
        if(!uri.toString().equals(expectedValue2)){
            fail();
        }
    }
}
