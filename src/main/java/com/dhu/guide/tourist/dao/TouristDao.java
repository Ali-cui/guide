package com.dhu.guide.tourist.dao;

import com.dhu.guide.entities.Audio;
import com.dhu.guide.entities.Image;
import com.dhu.guide.tourist.entities.*;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @Author: Ali.cui
 * @Date: 2019/12/7 20:29
 */
@Mapper
public interface TouristDao {
    @Select("SELECT * FROM scenicspot")
    List<TouristPoint> getAlltouristattraction();
    @Select("SELECT * FROM scenicspot where type=1")
    List<TouristPoint> getAlltourist1attraction();
    @Select("SELECT * FROM scenicspot where type=2")
    List<TouristPoint> getAllHelpPoint();
    @Select("SELECT * FROM scenicspot where type=3")
    List<TouristPoint> getAllServicePoint();
    @Update("UPDATE scenicspot SET longitude=#{longitude},latitude=#{latituade},type=#{type},introduction=#{introduction} WHERE addressname=#{addressname}")
    void modifyScenicspot(@Param("longitude")Double longitude,
                          @Param("latituade")Double latitude,
                          @Param("addressname")String addressname,
                          @Param("type")Integer type,
                          @Param("introduction")String introduction);

    @Insert("INSERT INTO scenicspot (longitude,latitude,addressname,type,introduction) VALUES(#{longitude},#{latitude},#{addressname},#{type},#{introduction})")
    void addScenicspot(@Param("longitude")Double longitude,
                       @Param("latitude")Double latitude,
                       @Param("addressname")String addressname,
                       @Param("type")Integer type,
                       @Param("introduction")String introduction);

    @Select("SELECT * FROM scenicspot WHERE addressname=#{addressname}")
    TouristPoint getScenicspotByname(@Param("addressname")String addressname);


    @Delete("DELETE FROM scenicspot WHERE addressname=#{addressname}")
    void deletePointByname(@Param("addressname")String addressname);

    //获取音频文件
    @Select("SELECT * FROM audio WHERE addressname=#{addressname}")
    @Results({
            @Result(column="audio",property="audio")})
    Audio getAudioByAddressname(@Param("addressname")String addressname);
    //插入音频文件
    @Insert("INSERT INTO audio(addressname,audio) VALUES(#{addressname}, #{audio})")
    void addAudio(@Param("addressname")String addressname,
                  @Param("audio")byte[] audio);

    @Update("UPDATE audio SET audio=#{audio} where addressname=#{addressname}")
    void updateAudio(@Param("addressname")String managername,@Param("audio")byte[] audio);

    //插入评论文件
    @Insert("INSERT INTO comment(addressname,image,time) VALUES(#{addressname},#{image},#{time})")
    void addComment(@Param("addressname")String addressname,
                  @Param("image")byte[] image,
                    @Param("time")Date date);
    //更新评论
    @Update("UPDATE comment SET comments=#{comments} where addressname=#{addressname}")
    void updateComment(@Param("addressname")String addressname,@Param("comments")String comment);
    //删除评论
    @Delete("DELETE FROM comment WHERE addressname=#{addressname}")
    void deleteComment(@Param("addressname")String addressname);
    //获取评论文件
    @Select("SELECT * FROM comment WHERE addressname=#{addressname}")
    Comment getCommentByAddressname(@Param("addressname")String addressname);
    //按照addressname获取一组图片
    @Select("SELECT * FROM comment WHERE addressname=#{addressname} GROUP BY addressname order by time desc")
    List<Comment> getCommentGroupByaddressname(@Param("addressname")String addressname);
    //通过id获取图片文件
    @Select("SELECT * FROM comment WHERE id=#{id}")
    Image getCommentById(@Param("addressname")Integer id);
    //获取评论数量
    @Select("SELECT count(*) FROM comment where addressname=#{addressname}")
    int getCommentTotalNumber(@Param("addressname")String addressname);
    //按组获得评论数量
    @Select("SELECT count(*) FROM comment where addressname=#{addressname} GROUP BY addressname")
    int getCommentTotalNumberGroupByAddressname(@Param("addressname")String addressname);
    //获取评论文件
    @Select("SELECT * FROM image WHERE addressname=#{addressname} ordered by likes desc limited 1")
    Image getCommentOrderedByLikes(@Param("addressname")String addressname);


    //删除图片
    @Delete("DELETE FROM image WHERE addressname=#{addressname}")
    void deleteImage(@Param("addressname")String addressname);
    //获取图片文件
    @Select("SELECT * FROM image WHERE addressname=#{addressname}")
    Image getImageByAddressname(@Param("addressname")String addressname);
    //按照addressname获取一组图片
    @Select("SELECT * FROM image WHERE addressname=#{addressname} GROUP BY addressname")
    List<Image> getImageGroupByaddressname(@Param("addressname")String addressname);
    //通过id获取图片文件
    @Select("SELECT * FROM image WHERE id=#{id}")
    Image getImageById(@Param("addressname")Integer id);
    //获取图片数量
    @Select("SELECT count(*) FROM image where addressname=#{addressname}")
    int getImageTotalNumber(@Param("addressname")String addressname);
    //按组获得图片数量
    @Select("SELECT count(*) FROM image where addressname=#{addressname} GROUP BY addressname")
    int getImageTotalNumberGroupByAddressname(@Param("addressname")String addressname);
    //获取图片文件
    @Select("SELECT * FROM image WHERE addressname=#{addressname} ordered by likes desc limited 1")
    Image getImageOrderedByLikes(@Param("addressname")String addressname);
    //插入图片文件
    @Insert("INSERT INTO image(addressname,image,likes) VALUES(#{addressname},#{image},#{likes})")
    void addImage(@Param("addressname")String addressname,
                  @Param("image")byte[] image,
                  @Param("likes")Integer likes);
    //更新图片
    @Update("UPDATE image SET image=#{image} where addressname=#{addressname}")
    void updateImage(@Param("addressname")String managername,@Param("image")byte[] image);

    //更新点赞数目
    @Update("UPDATE image SET likes=likes+1 where addressname=#{addressname}")
    void updateLikes(@Param("addressname")String managername);






    //按名称获取已注册游客
    @Select("SELECT * FROM tourist WHERE name=#{name}")
    Tourist getTouristByName(@Param("name")String name);
    //获取所有已注册游客
    @Select("SELECT * FROM tourist")
    List<Tourist> getAllTourists();
    @Insert("Insert into tourist(name,email,password) VALUES(#{name},#{email},#{password})")
    void insertTourist(@Param("name")String name,@Param("password")String password,@Param("email")String email);



    @Insert("INSERT INTO logintime(name,logintime) VALUES(#{name},#{logintime})")
    void insertLoginTime(@Param("name")String name, @Param("logintime")Date date);

    @Delete("DELETE FROM logintime WHERE name=#{name}")
    void deleteLoginUser(@Param("name")String name);

    @Select("SELECT * FROM logintime WHERE name=#{name}")
    List<LoginTime> selectLogintimeByname(@Param("name")String name);

    @Select("SELECT count(*) FROM logintime where logintime>#{logintimefirst} and logintime<#{logintimesecond}")
    int countLoginTimesInOneDay(@Param("logintimefirst")Date date1,@Param("logintimesecond")Date date2);

    @Select("SELECT * FROM emergency")
    List<Emergency> selectEmergency();
    @Insert("INSERT INTO emergency(idcard,latitude,longitude,assist,situation,name) VALUES(#{idcard},#{latitude},#{longtitude},#{assist},#{situation},#{name})")
    void insertEmergency(      @Param("idcard")String idcard,
                               @Param("latitude")Double latitude,
                               @Param("longtitude")Double longtitude,
                               @Param("assist")String assist,
                               @Param("situation")String situation,
                               @Param("name")String name);

    @Delete("delete from emergency")
    void deleteEmergency();
    @Select("SELECT * FROM location WHERE idcard=#{idcard}")
    Location getLocationByIdcard(@Param("idcard")String idcard);

    @Insert("INSERT INTO location(name,latitude,longitude,idcard) VALUES(#{name},#{latitude},#{longtitude},#{idcard})")
    void insertLocation(@Param("name")String name,
                               @Param("latitude")Double latitude,
                               @Param("longtitude")Double longtitude,
                               @Param("idecard")String idcard);

    @Update("UPDATE Location SET latitude=#{latitude},longtitude=#{longtitude} where name=#{name}")
    void updateLocation(@Param("name")String name,
                               @Param("latituede")Double latitude,
                               @Param("longtitude")Double longtitude);
    @Delete("delete from location")
    void deleteLocation();
}
