package com.dhu.guide.tourist.entities;

/**
 * @Author: Ali.cui
 * @Date: 2020/1/22 22:50
 */
public class Location {
    private String name;
    private Double latitude;
    private Double longitude;
    private String idcard;

    public String getIdcard() {
        return idcard;
    }

    public Location() {
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Location(String name, Double latitude, Double longitude,String idcard) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.idcard=idcard;
    }

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

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", idcard='" + idcard + '\'' +
                '}';
    }
}
