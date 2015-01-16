package com.lee.zun.lol_recentgame_api.api;

/**
 * Created by Namhyun. Gu on 2015-01-15.
 */
public class RiotApi {
    public static RiotApi mInstsance;

    public static synchronized RiotApi newInstance() {
        if (mInstsance == null) {
            mInstsance = new RiotApi();
        }
        return mInstsance;
    }

}
