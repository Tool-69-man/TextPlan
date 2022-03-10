package com.xroad.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.alibaba.fastjson.JSON;
import com.xroad.entity.Day;
import com.xroad.entity.Thing;
import com.xroad.util.RedisUtil;
import com.xroad.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.Map;

@Service
public class DayService {
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    DayDao dayDao;


//    redis 存的key
//
//
//"day"+did
// 单个值  "dayTimeForStart"+start    update 覆盖

//redis 数据
//    allDate  update、delete 时 要更新某一个值
//   某天的 "Date:"+localDate          add,update、delete 时 要更新 (某一个值)

// 数据库改变后 我是直接（从数据库）覆盖方便 但效率低  不理想


//    update、delete 时 要更新某一个值
    // 方法一  根据索引修改list中的某条数据   移除N个值为value
    // 方法二  更新数据库后 直接 从数据库中重新赋值





    //传入日期
    //TODO 应该去做，有某些原因没做的.很多IDE能够定位todo
    public  String getByDate(LocalDate localDate){


        if (redisUtil.hasKey("Date:"+localDate)){
            System.out.println("redis 有 Date:"+localDate);
        }else {
            System.out.println("缓存没有,数据库放入");
            redisUtil.lSet("Date:"+localDate,dayDao.getByDate(localDate),3600);
//            redisUtil.lSet("Date:"+localDate,dayDao.getByDate(localDate));
        }

        System.out.println("数据库获取"+dayDao.getByDate(localDate));
        System.out.println("redis获取"+redisUtil.lGet("Date:"+localDate,0,-1));
//        System.out.println("redis获取"+redisUtil.lGet("Date:"+localDate,0,-1).toString());
//        return redisUtil.lGet("Date:"+localDate,0,-1).toString();
        return redisUtil.lGet("Date:"+localDate,0,-1).toString();
    }




    public  String getAll(){


        if (redisUtil.hasKey("allDate")){
            System.out.println("allDate");
        }else {
            System.out.println("缓存没有allDate,数据库放入");
            redisUtil.lSet("allDate",dayDao.getAll(),3600);
        }
//        System.out.println("数据库获取"+dayDao.getByDate(localDate));
//        System.out.println("redis获取"+redisUtil.lGet("Date:"+localDate,0,-1).toString());
        return redisUtil.lGet("allDate",0,-1).toString();
    }

    //单个值
    public  String getId(Integer did){

        if (redisUtil.hasKey("day"+did)){
            System.out.println("day"+did);
        }else {
            System.out.println("缓存没有,数据库放入day"+did);
            redisUtil.set("day"+did,dayDao.getId(did),3600);
        }
//        System.out.println("数据库获取"+dayDao.getByDate(localDate));
//        System.out.println("redis获取"+redisUtil.lGet("Date:"+localDate,0,-1).toString());
        return redisUtil.get("day"+did).toString();

    }


    public  String getDayBySE(LocalDateTime start ){

        if (redisUtil.hasKey("dayTimeForStart"+start)){
            System.out.println("dayTimeForStart"+start);
            return JSON.toJSONString(redisUtil.get("dayTimeForStart"+start));
        }else {
            System.out.println("缓存没有,数据库放入dayTimeForStart"+start);

            Day day = dayDao.getDayBySE(start);
            System.out.println(day);
            if (day!=null){//数据库有这条数据 才放入redis
                redisUtil.set("dayTimeForStart"+start,day,3600);
                return JSON.toJSONString(redisUtil.get("dayTimeForStart"+start));
            }

//            day.getStart().toLocalDate();
//            day.getStart().getHour();
//            day.getStart().getMinute();
//            day.getStart().getSecond();



        }
//        System.out.println("数据库获取"+dayDao.getByDate(localDate));
//        System.out.println("redis获取"+redisUtil.lGet("Date:"+localDate,0,-1).toString());
//        return new  Day().toString();
        return "FALSE";
    }


    //增加 redis 数据库 单个数值
    //修改 数组的
    public void add(Map<String,Object> map){

        //时间 数据检验





        //数据库增加
        dayDao.add(map);
        //获取更新当前日期

//        LocalDate localDate = (LocalDate) map.get("start");//不能直接转

//        String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        LocalDate localDate = LocalDate.parse(DateUtil.formatDateTime(date));

//
//        Integer tid = (Integer) map.get("tid");
//
//        Integer did;

        LocalDateTime start  = (LocalDateTime) map.get("start");
        LocalDate localDate = start.toLocalDate();


//        LocalDateTime  end;
//
//
//       String text;
//       String feel;
//
//       Thing thing;

        redisUtil.set("dayTimeForStart"+start,dayDao.getDayBySE(start),3600); //只是设置新对象就查数据库 效率低
//        redisUtil.set("dayTimeForStart"+start,new Day(),3600);


        if (redisUtil.hasKey("Date:"+localDate)){
            System.out.println("add 更新redis 数组 "+localDate);
            redisUtil.lSet("Date:"+localDate,dayDao.getByDate(localDate),3600);
        }





    }




    //更新 redis  数据库


    public void update(Day day){

        dayDao.update(day);
        LocalDateTime start=day.getStart();
        LocalDate localDate =start.toLocalDate();


//        redisUtil.lUpdateIndex("Date:"+localDate,,day);

        if (redisUtil.hasKey("dayTimeForStart"+start)){
            System.out.println(" update更新redis dayTimeForStart数组 "+start);
            redisUtil.set("dayTimeForStart"+start,dayDao.getDayBySE(start),3600);

        }

        if( redisUtil.hasKey("Date:"+localDate)) {
            System.out.println("更新redis Date:localDate数组 "+localDate);
            redisUtil.lSet("Date:"+localDate,dayDao.getByDate(localDate),3600);
        }



    }









    public void deleteById(Integer did){
        if (redisUtil.hasKey("day"+did)){
            System.out.println("缓存删除day"+did);
            redisUtil.del("day"+did);
        }
//        System.out.println("数据库获取"+dayDao.getByDate(localDate));
//        System.out.println("redis获取"+redisUtil.lGet("Date:"+localDate,0,-1).toString());
//        return redisUtil.lGet("day"+did,0,-1).toString();

        System.out.println("数据库删除");
        dayDao.deleteById(did);

    }



    public void deleteByStart(LocalDateTime start){

        System.out.println("数据库删除start");
        dayDao.deleteByStart(start);


        //数据库都删了 就更新一下redis
        if (redisUtil.hasKey("dayTimeForStart"+start)){
            System.out.println("缓存删除dayTimeForStart"+start);
            redisUtil.del("dayTimeForStart"+start);
        }

        //将已有 redis 数组直接覆盖
        LocalDate localDate = start.toLocalDate();
        if( redisUtil.hasKey("Date:"+localDate)) {
            System.out.println("更新redis Date:localDate数组 "+localDate);


            redisUtil.lSet("Date:"+localDate,dayDao.getByDate(localDate),3600);
            System.out.println("又set进去");
//            redisUtil.lSet("Date:"+localDate,dayDao.getByDate(localDate));
        }


    }//











}
