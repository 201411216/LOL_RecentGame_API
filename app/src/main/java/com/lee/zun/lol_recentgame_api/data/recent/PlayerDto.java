package com.lee.zun.lol_recentgame_api.data.recent;

/**
 * Created by Namhyun, Gu on 2015-01-15.
 */
public class PlayerDto {
    private int championId;
    private long summonerId;
    private int teamId;

    public int getChampionId() {
        return championId;
    }

    public long getSummonerId() {
        return summonerId;
    }

    public int getTeamId() {
        return teamId;
    }
}
