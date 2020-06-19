package com.dhu.guide.tourist.entities;

/**
 * @Author: Ali.cui
 * @Date: 2020/3/30 15:35
 */
public class Emergency {
    Double latitude;
    Double longitude;
    String assist;
    String situation;
    String idcard;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAssist() {
        return assist;
    }

    public void setAssist(String assist) {
        this.assist = assist;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    @Override
    public String toString() {
        return "Emergency{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", assist='" + assist + '\'' +
                ", situation='" + situation + '\'' +
                ", idcard='" + idcard + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
