package com.dhu.guide.tourist.controller;


import com.dhu.guide.tourist.entities.TouristPoint;
import com.dhu.guide.tourist.service.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Author: Ali.cui
 * @Date: 2020/1/28 18:23
 */
@Controller
public class AjaxForTouristController {
    @Autowired
    TouristService touristService;
    @PostMapping("/guide/gethelppoint")
    @ResponseBody
    public List<TouristPoint> getHelpPoint(){
        return touristService.getAllHelpPoint();
    }


}
