package com.dhu.guide.tourist.entities;

import java.util.Date;

/**
 * @Author: Ali.cui
 * @Date: 2020/1/22 23:10
 */
public class LoginTime {
    private String name;
    private Date logintime;

    public LoginTime(String name, Date logintime) {
        this.name = name;
        this.logintime = logintime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    @Override
    public String toString() {
        return "LoginTime{" +
                "name='" + name + '\'' +
                ", logintime=" + logintime +
                '}';
    }
}
