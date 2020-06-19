package com.dhu.guide.utils;

import com.dhu.guide.entities.Manager;
import com.dhu.guide.service.ManagerService;
import com.dhu.guide.tourist.entities.LoginTime;
import com.dhu.guide.tourist.entities.Tourist;
import com.dhu.guide.tourist.service.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: Ali.cui
 * @Date: 2019/12/5 13:28
 */
@Component
public class LoginJudgement {
    @Autowired
     ManagerService managerService;
    @Autowired
    TouristService touristService;
    public static List<String> managers=new ArrayList<String>();
    public  boolean managerIsExist(String username){
    for(
    Manager manager:managerService.getAll()){
        if(manager.getManagername().equals(username)){
            return true;
        }
    }
    return false;
    }
    public boolean touristIsExist(String username){
        for(
                Tourist tourist:touristService.getAllTourists()){
            if(tourist.getName().equals(username)){
                return true;
            }
        }
        return false;
    }
    public static void addLoginUsers(String username){
    managers.add(username);

    }
    /**
     * @title: dateCompare
     * @description: 比较日期大小
     * @param date1 日期1
     * @param date2 日期2
     * @return
     */
    public static int dateCompare(Date date1, Date date2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateFirst = dateFormat.format(date1);
        String dateLast = dateFormat.format(date2);
        int dateFirstIntVal = Integer.parseInt(dateFirst);
        int dateLastIntVal = Integer.parseInt(dateLast);
        if (dateFirstIntVal > dateLastIntVal) {
            return 1;
        } else if (dateFirstIntVal < dateLastIntVal) {
            return -1;
        }
        return 0;
    }
    public  boolean hasUserLoginToday(String username) throws ParseException {
        for (LoginTime loginTime:touristService.getLogintimeByname(username)
        ) {
            if (dateCompare(loginTime.getLogintime(),new Date())==0){
                return true;
            }
        }
        return false;
    }
}
