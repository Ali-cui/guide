package com.dhu.guide.controller;

import com.dhu.guide.entities.Manager;
import com.dhu.guide.service.ManagerService;
import com.dhu.guide.tourist.entities.TouristPoint;
import com.dhu.guide.tourist.service.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @Author: Ali.cui
 * @Date: 2019/12/4 22:06
 */
@Controller
public class TestController {
    @Autowired
    ManagerService managerService;
    @Autowired
    TouristService touristService;
    @RequestMapping("/test")
    public String getManagerByname(String name){

       return "test";

    }
    @RequestMapping("/test2")
    @ResponseBody
public List<TouristPoint> getAll(){
    return  touristService.getAllTouristPoint();
    }
    @RequestMapping("/audio")
    public String getManagerBynam(){

        return "audio";
    }
    @RequestMapping("/uploadimageindex")
    public String uploadImage(){

        return "tourist/touristupload";

    }
}
