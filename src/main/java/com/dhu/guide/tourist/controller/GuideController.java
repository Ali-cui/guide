package com.dhu.guide.tourist.controller;

import com.dhu.guide.entities.Image;
import com.dhu.guide.tourist.entities.Comment;
import com.dhu.guide.tourist.entities.Emergency;
import com.dhu.guide.tourist.entities.Location;
import com.dhu.guide.tourist.entities.TouristPoint;
import com.dhu.guide.tourist.service.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * @Author: Ali.cui
 * @Date: 2019/12/7 18:23
 */
@Controller
public class GuideController {
    @Autowired
    TouristService touristService;
    @RequestMapping("/guide")
    public String toGuide(Model model){
        List<TouristPoint> touristPoints=touristService.getAllTouristPoint();
        model.addAttribute("touristpoints",touristPoints);
        return "guide";
    }

    @ResponseBody
    @PostMapping("/guide/getAllTouristPoint")
    public List<TouristPoint> getAllTouristPoint(){
        List<TouristPoint> touristPoints=touristService.getAllTouristPoint();

        return touristPoints;
    }
    @RequestMapping("/uploadimage/{addressname}")
    @ResponseBody
    public String uploadimage(@PathVariable String addressname,@RequestParam("mFile") MultipartFile file) throws IOException {
        byte[] byteFile=file.getBytes();
        int totalgroup=touristService.getImageTotalGroupByAddressname(addressname);
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
    @PostMapping("/uploadcommentimage/{addressname}")
    @ResponseBody
    public String uploadImageFile(@RequestParam("mFile") MultipartFile file,@PathVariable String addressname) throws IOException {

        byte[] byteFile=file.getBytes();

        touristService.insertComment(addressname,byteFile,new Date());
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


    @RequestMapping(value = "/getimage/{addressname}")
    public String getImage(@PathVariable String addressname, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        System.out.println("imageid: " + addressname);
        OutputStream os = null;
        ByteArrayInputStream bis = null;
        byte[] bytes=touristService.getImageByte(addressname);
        try {
            //根据ID获取实体
            bis = new ByteArrayInputStream(bytes);
            os = response.getOutputStream();
            int count = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((count = bis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
                os.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            bis.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "personalCenter";
    }


    //将二进制数返回给前端
    @RequestMapping(value = "/player/{addressname}")
    public ModelAndView getAudio(@PathVariable String addressname, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        System.out.println("audioid: " + addressname);
        String range = request.getHeader("Range");
        String[] rs = range.split("\\=");
        range = rs[1].split("\\-")[0];
        OutputStream os = response.getOutputStream();
        byte[] bytes=touristService.getAudioByte(addressname);
        InputStream oInputStream = new BufferedInputStream(new ByteArrayInputStream(bytes));
        long length = bytes.length;
        System.out.println("file length : " + length);
        // 播放进度
        int count = 0;
        // 播放百分比
        int percent = (int)(length);
        int irange = Integer.parseInt(range);
        length = length - irange;
        response.addHeader("Accept-Ranges", "bytes");
        response.addHeader("Content-Length", length + "");
        response.addHeader("Content-Range", "bytes " + range + "-" + length + "/" + length);
        response.addHeader("Content-Type", "audio/mpeg;charset=UTF-8");
        int len = 0;
        byte[] b = new byte[1024];
        while ((len = oInputStream.read(b)) != -1) {
            os.write(b, 0, len);
            count += len;
            if(count >= percent){
                break;
            }
        }
        System.out.println("count: " + count + ", percent: " + percent);
        oInputStream.close();
        os.close();
        return null;
    }

    @RequestMapping("/showimages/{addressname}")
     public String showImage(@PathVariable("addressname")String addressname,Model model){
        model.addAttribute("addressname",addressname);
        return "tourist/comment";
    }

    @RequestMapping("/getimagetotal/{addressname}")
    @ResponseBody
    public int getImagetotal(@PathVariable String addressname){
       return touristService.getImageTotalGroupByAddressname(addressname);
    }
//展示图片
    @RequestMapping(value = "/showimage/{addressname}/{id}")
    @ResponseBody
    public String showimage(@PathVariable String addressname,@PathVariable Integer id, HttpServletRequest request,
                           HttpServletResponse response) throws Exception {

        System.out.println("imageid: " + addressname);
        List<Comment> comments=touristService.getCommentByName(addressname);
        byte[] bytes=null;

        for (int i = 0; i < comments.size(); i++) {
            if (id.equals(i)){
               bytes= comments.get(i).getImage();}
        }
        if (bytes==null){
            return "failed";
        }
        OutputStream os = null;
        ByteArrayInputStream fis = null;

        try {

            fis = new ByteArrayInputStream(bytes);
            os = response.getOutputStream();
            int count = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
                os.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            fis.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

    @GetMapping("/guide/emergencyguide")
    public String emergencyGuide(HttpSession session,Model model){
        System.out.println(123);
        Object touristid=session.getAttribute("touristid");
        Object name=session.getAttribute("logintourist");
        Double latitude=touristService.getLocationByIdcard((String) touristid).getLatitude();
        Double longitude=touristService.getLocationByIdcard((String) touristid).getLatitude();
        Location location=new Location((String) name,latitude,longitude,(String) touristid);
        model.addAttribute("location",location);
        return "emergencyguide";
    }
    @PostMapping("/guide/emergencyhandle/{idcard}")
    public String emergencyHandle(Emergency emergency){
        touristService.insertEmergency(emergency.getIdcard(),emergency.getLongitude(),emergency.getLatitude(),emergency.getAssist(),emergency.getSituation(),emergency.getName());
        return "redirect：guide";
    }


    @RequestMapping("/guide/commentup/{addressname}")

    public String commentUp(@PathVariable String addressname,String comment) {
        touristService.uploadComment(addressname,comment);
        return "/showimages/"+addressname;
    }
    @RequestMapping("/guide/getlocationdata/{id}")
    @ResponseBody
    public Object bookAll(String str,@PathVariable String id) {
        System.out.println("Android端传过来的值："+str);
        System.out.println(id);
        return "已经成功建立链接";
    }





}
