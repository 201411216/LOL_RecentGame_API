package com.lee.zun.lol_recentgame_api.data.recent;

/**
 * Created by admin on 2015-01-20.
 */
public class SummonerIdDto {

    private String summonerIdStr;
    private long id;
    private String name;
    private int profileIconId;
    private long revisionDate;
    private long summonerLevel;

    public SummonerIdDto(String summonerIdStr, long id, String name, int profileIconId, long revisionDate, long summonerLevel) {
        this.summonerIdStr = summonerIdStr;
        this.id = id;
        this.name = name;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
        this.summonerLevel = summonerLevel;
    }

    public String getSummonerIdStr() {
        return summonerIdStr;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public long getRevisionDate() {
        return revisionDate;
    }

    public long getSummonerLevel() {
        return summonerLevel;
    }
}