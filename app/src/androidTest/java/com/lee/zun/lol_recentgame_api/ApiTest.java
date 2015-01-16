package com.lee.zun.lol_recentgame_api;

import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Log;

import com.lee.zun.lol_recentgame_api.api.RecentGameUriBuilder;

/**
 * Created by Namhyun, Gu on 2015-01-16.
 */
public class ApiTest extends AndroidTestCase {
    private String LOG_TAG = this.getClass().getSimpleName();

    public void testGetUri(){
        Uri uri = new RecentGameUriBuilder().reigon("kr").summonerId(100).build();
        Log.d(LOG_TAG, uri.toString());
    }
}
