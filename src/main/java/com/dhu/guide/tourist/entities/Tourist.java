package com.dhu.guide.tourist.entities;

/**
 * @Author: Ali.cui
 * @Date: 2019/12/7 14:07
 */
public class Tourist {
   private String name;
   private String email;
   private String password;
   private String idcard;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdacard() {
        return idcard;
    }

    public void setIdacard(String idacard) {
        this.idcard = idacard;
    }

    @Override
    public String toString() {
        return "Tourist{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", idacard=" + idcard +
                '}';
    }
}
