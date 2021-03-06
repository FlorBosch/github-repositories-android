package com.fbosch.assignment.githubrepositories.data.model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import static com.fbosch.assignment.githubrepositories.util.DateUtil.parseDate;


@Entity(tableName = "repositories")
public class Repository implements Parcelable {

    @PrimaryKey
    private final Long id;
    private final String name;
    private final String description;
    private final String language;

    @SerializedName("updated_at")
    private final String lastUpdate;

    @SerializedName("stargazers_count")
    private final int stars;

    @SerializedName("forks_count")
    private final int forks;

    public Repository(Long id, String name, String description, String language, String lastUpdate,
                      int stars, int forks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.language = language;
        this.lastUpdate = lastUpdate;
        this.stars = stars;
        this.forks = forks;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public int getStars() {
        return stars;
    }

    public int getForks() {
        return forks;
    }

    public String getStarsAsString() {
        return String.valueOf(stars);
    }

    public String getForksAsString() {
        return String.valueOf(forks);
    }

    public String getDateFormatted() {
        return parseDate(getLastUpdate());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.language);
        dest.writeString(this.lastUpdate);
        dest.writeInt(this.stars);
        dest.writeInt(this.forks);
    }

    protected Repository(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.description = in.readString();
        this.language = in.readString();
        this.lastUpdate = in.readString();
        this.stars = in.readInt();
        this.forks = in.readInt();
    }

    public static final Creator<Repository> CREATOR = new Creator<Repository>() {
        @Override
        public Repository createFromParcel(Parcel source) {
            return new Repository(source);
        }

        @Override
        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };
}
