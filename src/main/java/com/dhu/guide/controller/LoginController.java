package com.dhu.guide.controller;

import com.dhu.guide.service.ManagerService;
import com.dhu.guide.utils.LoginJudgement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author: Ali.cui
 * @Date: 2019/11/28 15:00
 */
@Controller
public class LoginController {
    @Autowired
    ManagerService managerService;
    @Autowired
    LoginJudgement loginJudgement;
    //处理管理员登录的请求
    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){

        if(loginJudgement.managerIsExist(username)&&managerService.getManagerbyName(username).getPassword().equals(password)){
           LoginJudgement.addLoginUsers(username);
            session.setAttribute("loginmanager",username);
            return "redirect:/indexofmanager";
        }else {
            map.put("msg","用户名或密码错误");
            return "login";
        }

    }

}
