package com.lee.zun.lol_recentgame_api.data.recent;

import java.util.List;

/**
 * Created by Namhyun, Gu on 2015-01-15.
 */
public class GameDto {
    private int championId;
    private long createDate;
    private List<PlayersDto> fellowPlayers;
    private long gameId;
    private String gameMode;
    private String gameType;
    private boolean invalid;
    private int ipEarned;
    private int level;
    private int mapId;
    private int spell1;
    private int spell2;
    private RawStatsDto stats;
    private String subType;
    private int teamId;

    public int getChampionId() {
        return championId;
    }

    public long getCreateDate() {
        return createDate;
    }

    public List<PlayersDto> getFellowPlayers() {
        return fellowPlayers;
    }

    public long getGameId() {
        return gameId;
    }

    public String getGameMode() {
        return gameMode;
    }

    public String getGameType() {
        return gameType;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public int getIpEarned() {
        return ipEarned;
    }

    public int getLevel() {
        return level;
    }

    public int getMapId() {
        return mapId;
    }

    public int getSpell1() {
        return spell1;
    }

    public int getSpell2() {
        return spell2;
    }

    public RawStatsDto getStats() {
        return stats;
    }

    public String getSubType() {
        return subType;
    }

    public int getTeamId() {
        return teamId;
    }
}
