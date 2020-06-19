package com.dhu.guide.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.Map;

/**
 * @Author: Ali.cui
 * @Date: 2019/11/27 15:22
 */
@Controller
public class IndexController {
    @RequestMapping(value = {"/login","/"},method = RequestMethod.GET)
    public String hahaaa(){
        return "login";
    }
    @RequestMapping("/success")
    public String test(Map<String,Object> map){
        map.put("hello","你好");
        return "success";
    }
    @RequestMapping("/hello")
    @ResponseBody
    public  String hello(){
        return "你好";
    }
}
