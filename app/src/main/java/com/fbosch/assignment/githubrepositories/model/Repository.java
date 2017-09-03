package com.fbosch.assignment.githubrepositories.model;


import com.google.gson.annotations.SerializedName;


public class Repository {

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
}
