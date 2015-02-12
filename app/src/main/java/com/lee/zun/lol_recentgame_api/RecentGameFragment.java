package com.lee.zun.lol_recentgame_api;

import com.google.gson.Gson;
import com.lee.zun.lol_recentgame_api.api.GameUriBuilder;
import com.lee.zun.lol_recentgame_api.api.RiotApi;
import com.lee.zun.lol_recentgame_api.api.SummonerUriBuilder;
import com.lee.zun.lol_recentgame_api.data.recent.RecentGamesDto;
import com.lee.zun.lol_recentgame_api.data.summoner.SummonerIdDto;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2015-01-13.
 */

//TODO serch action prvider


public class RecentGameFragment {
    List<SummonerIdDto> summonerIdDtos = new ArrayList<SummonerIdDto>();
    RecentGamesDto recentGame = new RecentGamesDto();

    final String thezun = "The Zun";

    public void getSummonerID(String thezun) {
        try {
            String summonerIdStr = thezun;
            Response response = RiotApi.newInstance().getResponceFromBuilder(
                    new SummonerUriBuilder().region("kr").byName().summonerName(thezun));
            String result = response.body().string();
            JSONObject resultJson = new JSONObject();
            JSONArray resultJsonArray = resultJson.getJSONArray(thezun);
            long idLong = 0;
            String nameStr;
            int profileIconIdInt = 0;
            long revisionDateLong = 0;
            long summonerLevelLong = 0;
            for (int i = 0; i < resultJsonArray.length(); i++) {

                JSONObject resultObject = resultJsonArray.getJSONObject(i);
                idLong = Long.getLong(resultObject.getString("id"));
                nameStr = resultObject.getString("name");
                profileIconIdInt = Integer.getInteger(resultObject.getString("profileIconId"));
                revisionDateLong = Long.getLong(resultObject.getString("revisionDate"));
                summonerLevelLong = Long.getLong(resultObject.getString("summonerLevel"));
                //summonerIdDtos.add(new SummonerIdDto(summonerIdStr, idLong, nameStr, profileIconIdInt, revisionDateLong, summonerLevelLong));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getRecentGame() {
        long summonerIdLong = summonerIdDtos.get(0).getId();

        try {

            Response response = RiotApi.newInstance().getResponceFromBuilder(
                    new GameUriBuilder().region("kr").summonerId(summonerIdLong));
            String result = response.body().string();

            Gson gson = new Gson();
            recentGame = gson.fromJson(result, RecentGamesDto.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}