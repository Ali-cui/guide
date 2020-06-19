package com.dhu.guide.entities;

/**
 * @Author: Ali.cui
 * @Date: 2019/12/4 20:55
 */
public class Manager {
    private Integer id;
    private String managername;
    private Integer jobnumber;
    private String  password;
    private Integer accesslevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getManagername() {
        return managername;
    }

    public void setManagername(String managername) {
        this.managername = managername;
    }

    public Integer getJobnumber() {
        return jobnumber;
    }

    public void setJobnumber(Integer jobnumber) {
        this.jobnumber = jobnumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAccesslevel() {
        return accesslevel;
    }

    public void setAccesslevel(Integer accesslevel) {
        this.accesslevel = accesslevel;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", managername='" + managername + '\'' +
                ", jobnumber=" + jobnumber +
                ", password='" + password + '\'' +
                ", accesslevel='" + accesslevel + '\'' +
                '}';
    }
}
