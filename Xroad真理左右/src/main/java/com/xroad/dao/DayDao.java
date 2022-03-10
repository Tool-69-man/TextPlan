package com.xroad.dao;

import com.fasterxml.jackson.databind.JavaType;
import com.xroad.entity.Day;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
@Mapper
@Repository
public interface DayDao {

    //CRUD

    List<Day> getAll();

    Day getId(Integer did);

    int deleteById(Integer did);

    //    HashMap<String, Object> map = new HashMap<String, Object>();
//        map.put("i",19);
//        map.put("name","上帝");
//        map.put("pass","卡密");
//
//        userDao.addUser2(map);
    int add(Map<String,Object> map);

    //controller 中传入 Day
    int update(Day day);



    //传入当天日期  2000-12-12
//    @Select("select * from daybyday where  start LIKE '%#{date}%'" )
    List<Day> getByDate( @Param("date")LocalDate date);

    //按开始时间(唯一索引),和结束时间
    Day getDayBySE(LocalDateTime start );



    int deleteByStart(LocalDateTime start);

    //删除全部  或 多个 ids








}
