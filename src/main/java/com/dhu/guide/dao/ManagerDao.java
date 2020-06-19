package com.dhu.guide.dao;

import com.dhu.guide.tourist.entities.Emergency;
import com.dhu.guide.tourist.entities.Location;
import com.dhu.guide.entities.Manager;
import org.apache.ibatis.annotations.*;


import java.util.List;

/**
 * @Author: Ali.cui
 * @Date: 2019/12/4 20:26
 */
@Mapper
public interface ManagerDao {
   @Select("SELECT * FROM manager WHERE managername=#{managername}")
   @Results({@Result(column="id",property="id"),
           @Result(column="managername",property="managername"),
           @Result(column="password",property="password"),
           @Result(column="jobnumber",property="jobnumber"),
           @Result(column="accesslevel",property="accesslevel")})
 Manager getManagerbyName(@Param("managername")String managername);

    @Select("SELECT * FROM manager WHERE jobnumber=#{jobnumber}")
    @Results({@Result(column="id",property="id"),
            @Result(column="managername",property="managername"),
            @Result(column="password",property="password"),
            @Result(column="jobnumber",property="jobnumber"),
            @Result(column="accesslevel",property="accesslevel")})
    Manager getManagerbyJobnumber(@Param("jobnumber")Integer jobnumber);
 @Select("SELECT * FROM manager")
    List<Manager> getAll();

 @Insert("INSERT INTO manager(managername, password,jobnumber,accesslevel) VALUES(#{managername}, #{password},#{jobnumber},#{accesslevel})")
 void addManager(@Param("managername")String managername,
                 @Param("password")String password,
                 @Param("accesslevel")Integer accesslevel,
                 @Param("jobnumber")Integer jobnumber);

   @Delete("DELETE FROM manager WHERE managername=#{managername}")
   void deleteManager(@Param("managername")String managername);

   @Update("UPDATE manager SET managername=#{managername},password=#{password},jobnumber=#{jobnumber},accesslevel=#{accesslevel} where id=#{id}")
   void alertManager(@Param("id")Integer id,
                   @Param("managername")String managername,
                   @Param("password")String password,
                   @Param("accesslevel")Integer accesslevel,
                   @Param("jobnumber")Integer jobnumber);

    @Select("SELECT * FROM location")
    List<Location> getAllCurrentLocation();

    @Insert("INSERT into location(latitude,longitude,name,idcard) VALUES(#{latitude},#{longitude},#{name},#{idcard})")
    void insertLocation(@Param("latitude")Double latitude,
                        @Param("longitude")Double longitude,
                        @Param("name")String name,
                        @Param("idcard")String idcard);

    @Update("UPDATE location SET latitude=#{latitude},longitude=#{longitude} where name=#{name}")
    void updateLocation(@Param("latitude")Double latitude,
                        @Param("longitude")Double longitude,
                        @Param("name")String name);
    @Delete("delete from location")
    void deleteLocation();

    @Select("SELECT count(*) FROM emergency ")
    int getemergencyTotalNumber();
    @Select("SELECT* FROM emergency ")
    List<Emergency> getAllemergency();
    @Delete("Delete FROM emergency ")
    void deleteAllemergency();
    @Select("SELECT * FROM emergency where name=#{name}")
    Emergency getEmergencyByName(@Param("name")String name);
}
