package com.dhu.guide.tourist.entities;

import java.util.Arrays;
import java.util.Date;

/**
 * @Author: Ali.cui
 * @Date: 2020/3/31 16:56
 */
public class Comment {
    private String addressname;
    private String comments;
    private byte[] image;
    private Integer id;
    private Date time;

    public String getAddressname() {
        return addressname;
    }

    public void setAddressname(String addressname) {
        this.addressname = addressname;
    }

    public Comment() {
    }

    public Comment(String addressname) {
        this.addressname = addressname;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "addressname='" + addressname + '\'' +
                ", comments='" + comments + '\'' +
                ", image=" + Arrays.toString(image) +
                ", id=" + id +
                ", time=" + time +
                '}';
    }

    public Comment(String addressname, String comments, byte[] image, Integer id, Date time) {
        this.addressname = addressname;
        this.comments = comments;
        this.image = image;
        this.id = id;
        this.time = time;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
