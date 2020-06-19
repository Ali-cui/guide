package com.dhu.guide.tourist.controller;

import com.dhu.guide.service.ManagerService;
import com.dhu.guide.tourist.entities.LoginTime;
import com.dhu.guide.tourist.service.TouristService;
import com.dhu.guide.utils.LoginJudgement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * @Author: Ali.cui
 * @Date: 2019/11/28 15:00
 */
@Controller
public class TouristLoginController {
    @Autowired
    ManagerService managerService;
    @Autowired
    LoginJudgement loginJudgement;
    @Autowired
    TouristService touristService;
    //处理游客登录的请求
@RequestMapping("/guide/index")
public String touristIndex(){
    return "tourist/loginfortourist";
}
    @PostMapping("/guide/touristlogin")
    public String touristLogin(@RequestParam("username") String username, @RequestParam("password") String password,
                               Map<String,Object> map, HttpSession session) throws ParseException {
        if(loginJudgement.touristIsExist(username)&&touristService.getTouristByname(username).getPassword().equals(password)){
            session.setAttribute("logintourist",username);

            session.setAttribute("touristid",touristService.getTouristByname(username).getIdacard());

            if(loginJudgement.hasUserLoginToday(username)){

            }else {
                touristService.addlogintime(username,new Date());//在数据库中记录登录时间
            }

            return "redirect:/guide";
        }else{
            map.put("msg","用户名或密码错误");
            return "tourist/loginfortourist";
        }

    }
    @GetMapping("/guide/register")
    public String touristRegister(){
      return "tourist/register";
    }

    @PostMapping("/guide/handleregister")
    public String doTouristRegister(@RequestParam("registerUsername")String name,
                                    @RequestParam("registerPassword")String password,
                                    @RequestParam("registerEmail") String email){
    touristService.addTourist(name,password,email);
        System.out.println(name+password+email);
        return "tourist/loginfortourist";
    }

}
