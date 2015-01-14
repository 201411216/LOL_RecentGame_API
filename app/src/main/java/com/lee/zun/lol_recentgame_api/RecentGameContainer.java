package com.lee.zun.lol_recentgame_api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 2015-01-13.
 */

// Alt + Insert = getter setter

public class RecentGameContainer {
    public long summonerId;

    @SerializedName("Id")
    public int championId;

    public long createDate;
    public List<PlayersDto> fellowPlayers;
    public long gameId;
    public String gameMode;
    public String gameType;
    public int ipEarned;
    public int summonerLevel;
    public int mapId;
    public int spell1;
    public int spell2;
    public List<RawStatsDto> stats;

}
