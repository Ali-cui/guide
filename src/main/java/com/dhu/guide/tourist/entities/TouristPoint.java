package com.dhu.guide.tourist.entities;

/**
 * @Author: Ali.cui
 * @Date: 2019/12/7 20:06
 */
public class TouristPoint {
    private Double longitude;
    private Double latitude;
    private String addressname;
    private Integer type;
    private String introduction;

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getAddressname() {
        return addressname;
    }

    public void setAddressname(String addressname) {
        this.addressname = addressname;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "TouristPoint{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", addressname='" + addressname + '\'' +
                ", type=" + type +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
