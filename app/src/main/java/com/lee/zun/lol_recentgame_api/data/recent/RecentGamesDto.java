package com.lee.zun.lol_recentgame_api.data.recent;

import java.util.List;

/**
 * Created by Namhyun, Gu on 2015-01-15.
 */
public class RecentGamesDto {
    private List<GameDto> games;
    private long summonerId;

    public List<GameDto> getGames() {
        return games;
    }

    public long getSummonerId() {
        return summonerId;
    }
}
