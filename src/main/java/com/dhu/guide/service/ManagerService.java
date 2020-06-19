package com.dhu.guide.service;

import com.dhu.guide.dao.ManagerDao;
import com.dhu.guide.tourist.entities.Emergency;
import com.dhu.guide.tourist.entities.Location;
import com.dhu.guide.entities.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Ali.cui
 * @Date: 2019/12/4 21:48
 */
@Service
public class ManagerService {
     @Autowired
     ManagerDao managerDao;
    public Manager getManagerbyName(String name){
        return managerDao.getManagerbyName(name);
    }
    public void  add(String managername,String password,Integer accesslevel,Integer jobnumber){
     managerDao.addManager(managername,password,accesslevel,jobnumber);
    }
    public void deleteManager(String name){

        managerDao.deleteManager(name);
    }
    public List<Manager> getAll(){
     return  managerDao.getAll();
    }
    public void alertManager(Integer id,String managername,String password,Integer accesslevel,Integer jobnumber){
        managerDao.alertManager( id, managername,password,accesslevel,jobnumber);
    }

    public List<Location> getAllCurrentLocation(){
        return managerDao.getAllCurrentLocation();
    }

    public void deleteAllLocation(){
        managerDao.deleteLocation();
    }

    public void updateLocation(Double latitude,Double longitude,String name){
        managerDao.updateLocation(latitude,longitude,name);
    }
    public void insertLocation(Double latitude,Double longitude,String name,String idcard){
        managerDao.insertLocation(latitude,longitude,name,idcard);
    }

    public int getHelpNumber(){
        return managerDao.getemergencyTotalNumber();
    }
    public List<Emergency> getAllemergency(){
        return  managerDao.getAllemergency();
    }
    public void deleteAllemergency(){
        managerDao.deleteAllemergency();
    }
    public Emergency getEmergencyByName(String name){
        return managerDao.getEmergencyByName(name);
    }


}
