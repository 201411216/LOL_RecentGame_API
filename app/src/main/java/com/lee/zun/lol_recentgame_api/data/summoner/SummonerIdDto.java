package com.lee.zun.lol_recentgame_api.data.summoner;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 2015-01-20.
 */
public class SummonerIdDto implements Parcelable {
    private long id;
    private String name;
    private int profileIconId;
    private long revisionDate;
    private long summonerLevel;

    public static final Creator<SummonerIdDto> CREATOR = new Creator<SummonerIdDto>() {
        @Override
        public SummonerIdDto createFromParcel(Parcel source) {
            return new SummonerIdDto(source);
        }

        @Override
        public SummonerIdDto[] newArray(int size) {
            return new SummonerIdDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeInt(profileIconId);
        dest.writeLong(revisionDate);
        dest.writeLong(summonerLevel);
    }

    public SummonerIdDto() {
    }

    public SummonerIdDto(Parcel in) {
        id = in.readLong();
        name = in.readString();
        profileIconId = in.readInt();
        revisionDate = in.readLong();
        summonerLevel = in.readLong();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    public void setRevisionDate(long revisionDate) {
        this.revisionDate = revisionDate;
    }

    public void setSummonerLevel(long summonerLevel) {
        this.summonerLevel = summonerLevel;
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