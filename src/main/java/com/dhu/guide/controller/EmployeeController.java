package com.dhu.guide.controller;


import com.dhu.guide.dao.ManagerDao;
import com.dhu.guide.entities.Audio;
import com.dhu.guide.entities.Image;
import com.dhu.guide.entities.Manager;
import com.dhu.guide.service.ManagerService;
import com.dhu.guide.tourist.entities.Emergency;
import com.dhu.guide.tourist.entities.TouristPoint;
import com.dhu.guide.tourist.service.TouristService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Author: Ali.cui
 * @Date: 2019/11/28 19:55
 */
@Controller
public class EmployeeController {

    @Autowired
    ManagerService managerService;
    @Autowired
    TouristService touristService;
    //查询所有员工返回列表页面
    @GetMapping("/managers")
    public String list(Model model){
        List<Manager> managers=managerService.getAll();
        model.addAttribute("managers",managers);
        return "manager/list";
    }
    //来到添加员工页面
    @GetMapping("/manager")
    public String toAddPage(){
    //来到添加页面,查出所有的部门，在页面显示

        return "manager/add";
    }
    //员工添加
    //springMVC自动将请求参数和入参对象的属性进行一一绑定；要求了请求参数的名字和javaBean入参对象里面的属性名字是一样的
    @PostMapping("/manager")
    public String addEmp(Manager manager){
        System.out.println("保存的员工信息："+manager);
        managerService.add(manager.getManagername(),manager.getPassword(),manager.getAccesslevel(),manager.getJobnumber());

        //来到员工列表页面
        return "redirect:/managers";
    }
    @GetMapping("/manager/{managername}")
    public String toEditPage(@PathVariable String managername,Model model){
     Manager manager=managerService.getManagerbyName(managername);
        System.out.println(manager);
     model.addAttribute("manager",manager);
     //回到修改页面（add是一个修改添加二合一的页面）
     return "manager/edit";
    }
    //员工修改
    @PostMapping("/manageralert")
    public String updateManager(Manager manager){
        System.out.println("修改的员工数据："+manager);
        managerService.alertManager(manager.getId(),manager.getManagername(),manager.getPassword(),manager.getAccesslevel(),manager.getJobnumber());
       return "redirect:/managers";
    }
    //员工删除
    @GetMapping("/managerdelete/{managername}")
    public String deleteEmployee(@PathVariable String managername){
        System.out.println("删除"+managername);
        managerService.deleteManager(managername);
        return "redirect:/managers";
    }
    //管理POI点
    @GetMapping("/managepoint")
    public String managepoint(Model model){
        System.out.println(
                touristService.getAllTouristPoint()
        );
        model.addAttribute("points",touristService.getAllTouristPoint());
        return "manager/pointlist";
    }
    //添加POI点
    @GetMapping("/addpoint")
    public String addPoint(){
        return "manager/addpoint";

    }
    @PostMapping("/addpoint")
    public String addPoint(TouristPoint touristPoint){
        System.out.println("add"+touristPoint);
        touristService.addPoint(touristPoint.getLongitude(),touristPoint.getLatitude(),touristPoint.getAddressname(),touristPoint.getType(),touristPoint.getIntroduction());

        return "redirect:/managepoint";
    }
    //编辑POI点
    @GetMapping("/managepoint/{addressname}")
    public String editPoint(@PathVariable String addressname,Model model){
      model.addAttribute("point",touristService.getPointByname(addressname));
      return  "manager/editpoint";
    }
    @PostMapping("/editpoint")
    public String editPoint(TouristPoint touristPoint){
        System.out.println("edit"+touristPoint);
        touristService.editPoint(touristPoint.getLongitude(),touristPoint.getLatitude(),
                                 touristPoint.getAddressname(),touristPoint.getType(),touristPoint.getIntroduction());
      return "redirect:/managepoint";
    }
    @PostMapping("/deletepoint/{addressname}")
    public String deletePoint(@PathVariable String addressname){
        touristService.deletePointByname(addressname);
        return "redirect:/managepoint";
    }
    //编辑POI点的语音
    @GetMapping("/manageaudio/{addressname}")
    public String manage(@PathVariable String addressname,Model model){
        model.addAttribute("addressname",addressname);
        return  "upload";}
  //这里是音频
    @GetMapping("/upload/{addressname}")
    public String upload(@PathVariable String addressname,Model model){
        model.addAttribute("addressname",addressname);
        return "upload";
    }

//上传音频二进制文件
    @PostMapping("/file/{addressname}")
    @ResponseBody
    public String uploadFile(@RequestParam("mFile") MultipartFile file,@PathVariable String addressname) throws IOException {

        byte[] byteFile=file.getBytes();
        Audio audio=new Audio(addressname,byteFile);
        touristService.addaudio(audio);
//        String fileName = file.getOriginalFilename();
//        String suffix = fileName.substring(fileName.lastIndexOf('.'));
//        String newFileName = new Date().getTime() + suffix;
//        String path = "D:/test/";
//        File newFile = new File(path + newFileName);
        try {
//            file.transferTo(newFile);
            return "成功";
        }
        catch (Exception e){
            e.printStackTrace();
            return "失败";
        }
    }
    //上传图片的页面
    @GetMapping("/manageimage/{addressname}")
    public String manageimage(@PathVariable String addressname,Model model){
        model.addAttribute("addressname",addressname);
        return "tourist/touristupload";
    }
    @PostMapping("/uploadimage/{addressname}")
    @ResponseBody
    public String uploadImageFile(@RequestParam("mFile") MultipartFile file,@PathVariable String addressname) throws IOException {

        byte[] byteFile=file.getBytes();
        Image image=new Image(addressname,byteFile,0);
        touristService.addImage(image);
//        String fileName = file.getOriginalFilename();
//        String suffix = fileName.substring(fileName.lastIndexOf('.'));
//        String newFileName = new Date().getTime() + suffix;
//        String path = "D:/test/";
//        File newFile = new File(path + newFileName);
        try {
//            file.transferTo(newFile);
            return "成功";
        }
        catch (Exception e){
            e.printStackTrace();
            return "失败";
        }
    }

    //
    @RequestMapping("/analysis")
    public String analysistourist(){
        return "manager/analysisnumber";
    }
    //
    @RequestMapping("/managetouristmap")
    public String managetouristmap(){
        return "manager/touristsinmap";
    }

    @RequestMapping("/attendance")
    public String attendance(){
        return "manager/attendance";
    }

    @RequestMapping("/gethelpnumber")
    @ResponseBody
    public String getHelpNumber(){
        return Integer.toString(managerService.getHelpNumber());

    }
    @RequestMapping("/emergencylist")
    public String getEmergencylist(Model model){
        model.addAttribute("emergencys",managerService.getAllemergency());
        return "manager/emergencylist";
    }
    @RequestMapping("/deleteallhelp")
    public String deleteallhelp(Model model){
        managerService.deleteAllemergency();
        return "manager/emergencylist";
    }
    @RequestMapping("/gethelpmap/{name}")
    public String getHelpMap(@PathVariable("name")String name){
        System.out.println(name);
        return "manager/helpmap";
    }
    @RequestMapping("/emergencymap/{name}")
    @ResponseBody
    public Emergency forlhelpmap(@PathVariable("name") String name){

        return managerService.getEmergencyByName(name);
    }

}
