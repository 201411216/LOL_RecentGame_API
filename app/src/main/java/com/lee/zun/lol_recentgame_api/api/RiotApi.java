package com.lee.zun.lol_recentgame_api.api;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

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

    public Response getResponceFromBuilder(BaseUriBuilder builder) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                    .url(builder.getURL())
                    .build();
        Response response = client.newCall(request).execute();
        return response;
    }
}
