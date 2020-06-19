package com.dhu.guide.tourist.service;

import com.dhu.guide.entities.Audio;
import com.dhu.guide.entities.Image;
import com.dhu.guide.tourist.dao.TouristDao;
import com.dhu.guide.tourist.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: Ali.cui
 * @Date: 2019/12/7 20:35
 */
@Service
public class TouristService {
    @Autowired
    TouristDao touristDao;
    public List<TouristPoint> getAllTouristPoint(){
        return touristDao.getAlltouristattraction();
    }
    public List<TouristPoint> getAllHelpPoint(){
        return touristDao.getAllHelpPoint();
    }
    public TouristPoint getPointByname(String name){
        return touristDao.getScenicspotByname(name);
    }
    public void addPoint(Double longtitude,Double latituede,String addressname,Integer type,String introduction){
        touristDao.addScenicspot(longtitude,latituede,addressname,type,introduction);
    }
    public void editPoint(Double longtitude,Double latitude,String addressname,Integer type,String introduction){
        touristDao.modifyScenicspot(longtitude,latitude,addressname,type,introduction);
    }
    public void deletePointByname(String name){
        touristDao.deletePointByname(name);
    }
    public void addaudio(Audio audio){
        touristDao.addAudio(audio.getAddressname(),audio.getAudiobinary());
    }

    public byte[] getAudioByte(String addressname){
        return touristDao.getAudioByAddressname(addressname).getAudiobinary();
    }

    public void addImage(Image image){
        touristDao.addImage(image.getAddressname(),image.getImage(),image.getLikes());
    }

    public void modifyImage(String addressname,byte[] image){
        touristDao.updateImage(addressname,image);
    }
    public byte[] getImageByte(String addressname){
        return touristDao.getImageByAddressname(addressname).getImage();
    }

    public void deleteImage(String addressname){
        touristDao.deleteImage(addressname);
    }

    public int getImageTotal(String addressname){
        return touristDao.getImageTotalNumber(addressname);
    }
    public byte[] getImageByteById(Integer id){
        return touristDao.getImageById(id).getImage();
    }

    public List<Image> getImageGourpByAddressname(String addressname){
        return touristDao.getImageGroupByaddressname(addressname);
    }

    public int getImageTotalGroupByAddressname(String addressname){
        return  touristDao.getImageTotalNumberGroupByAddressname(addressname);
    }

    public Tourist getTouristByname(String name){
        return touristDao.getTouristByName(name);
    }

    public List<Tourist> getAllTourists(){
        return  touristDao.getAllTourists();
    }

    public void addTourist(String name,String password,String email){
        touristDao.insertTourist(name,password,email);
    }

    public void addlogintime(String name, Date date){
        touristDao.insertLoginTime(name,date);
    }

    public List<LoginTime> getLogintimeByname(String name){
        return touristDao.selectLogintimeByname(name);
    }

    public int getLoginTimesInADay(Date date1,Date date2){
       return touristDao.countLoginTimesInOneDay(date1,date2);
    }
    public void deleteLogintimeByname(String name){
        touristDao.deleteLoginUser(name);
    }

    public List<Emergency>  getEmergency(){
        return touristDao.selectEmergency();
    }
    public void insertEmergency(String idcard,Double longitude,double latitude,String assist,String situation,String name){
        touristDao.insertEmergency(idcard,latitude,longitude,assist,situation,name);
    }
    public void deleteEmergency(){
        touristDao.deleteEmergency();
    }

    public Location getLocationByIdcard(String idcard){
       return touristDao.getLocationByIdcard(idcard);
    }
    public void insertLocation(Location location){
        touristDao.insertLocation(location.getName(),location.getLatitude(),location.getLongitude(),location.getIdcard());
    }
    public void updateLocation(Location location){
        touristDao.updateLocation(location.getName(),location.getLatitude(),location.getLongitude());
    }

    public List<Comment> getCommentByName(String addressname){
        return touristDao.getCommentGroupByaddressname(addressname);
    }
    public int getCommentTotalGroupByaddressname(String addressname){
        return touristDao.getCommentTotalNumber(addressname);
    }
    public void insertComment(String addressname,byte[] image,Date date){
        touristDao.addComment(addressname,image,date);
    }
    public void uploadComment(String addressname,String comment){
        touristDao.updateComment(addressname,comment);
    }

}
