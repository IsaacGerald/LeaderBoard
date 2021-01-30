package com.example.volley.models;

import androidx.room.Entity;

@Entity(tableName = "learning_table")
public class Learners {
    private int id;
    private String name;
    private int hours;
    private String  badgeUrl;
    private String country;

    public Learners(String name, int hours, String badgeUrl, String country) {
        this.name = name;
        this.hours = hours;
        this.badgeUrl = badgeUrl;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Learners{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hours=" + hours +
                ", badgeUrl='" + badgeUrl + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
