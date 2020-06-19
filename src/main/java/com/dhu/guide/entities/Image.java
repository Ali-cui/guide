package com.dhu.guide.entities;

import java.util.Arrays;

/**
 * @Author: Ali.cui
 * @Date: 2020/1/6 15:58
 */
public class Image {
    private String addressname;
    private byte[] image;
    private Integer likes;


    public Image(String addressname, byte[] image, Integer likes) {
        this.addressname = addressname;
        this.image = image;
        this.likes = likes;

    }

    public String getAddressname() {
        return addressname;
    }

    public void setAddressname(String addressname) {
        this.addressname = addressname;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Image{" +
                "addressname='" + addressname + '\'' +
                ", image=" + Arrays.toString(image) +
                ", likes=" + likes +

                '}';
    }
}
