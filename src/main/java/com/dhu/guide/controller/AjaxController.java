package com.dhu.guide.controller;

import com.dhu.guide.dao.ManagerDao;
import com.dhu.guide.tourist.entities.Location;
import com.dhu.guide.entities.Manager;
import com.dhu.guide.tourist.service.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: Ali.cui
 * @Date: 2019/11/24 16:38
 */
@Controller
@RequestMapping("/")
public class AjaxController {
    @Autowired
    private ManagerDao managerDao;
    @Autowired
    TouristService touristService;
    @PostMapping("/checkmanagername")
    @ResponseBody
    public String checkmanagername(String managername){
    Manager manager =managerDao.getManagerbyName(managername);
      if(manager==null){
          return "该员工未注册，可以进行注册操作";
      }else {
          return  "该员工已注册，不可以进行注册操作";
      }
    }
    @PostMapping("/checkjobnumber")
    @ResponseBody
    public String checkjobnumber(Integer jobnumber){
        Manager manager =managerDao.getManagerbyJobnumber(jobnumber);
        if(manager==null){
            return "该员工未注册，可以进行注册操作";
        }else {
            return  "该员工已注册，不可以进行注册操作";
        }
    }
    @PostMapping("/gettouristdata")
    @ResponseBody
    public List<String> ajaxGetTouristData() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String date=format.format(new Date());
        Date date1=format.parse(date);
        System.out.println(date1);
        c.setTime(date1);
        c.add(Calendar.DATE, 1);
        Date end = c.getTime();

        List<String> days=new ArrayList<>();

        for (int i = 0; i <10 ; i++) {
            c.add(Calendar.DATE, -1);
            Date start = c.getTime();
            String qyt= format.format(start);//前一天
            days.add(qyt);
            days.add(new Integer(touristService.getLoginTimesInADay(start,end)).toString());
            end=start;
        }
        System.out.println(days);

        return days;
    }
    @PostMapping("/gettouristslocation")
    @ResponseBody
    public List<Location> getTouristsLocation(){
    return managerDao.getAllCurrentLocation();
    }

}
