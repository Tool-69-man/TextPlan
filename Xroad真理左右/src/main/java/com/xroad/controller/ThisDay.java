package com.xroad.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.xroad.config.DefaultSchedulingConfigurer;
//import com.xroad.config.schedule.JobsConfigTest;
import com.xroad.entity.Day;
import com.xroad.service.DayService;
import com.xroad.service.MailServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;


@Slf4j
@RestController
@CrossOrigin
public class ThisDay {
    @Autowired
    DayService dayService;

    @Autowired
    MailServer mailServer;


//    @Autowired
//    JobsConfigTest jobsConfigTest;



    //@DateTimeFormat(pattern = "yyyy-mm-dd")
    @RequestMapping("/date")//传入data
    public String getThisDate(String data) {//获取这个
//    public String getThisDate(Object data) {//获取这个

        System.out.println(data);



//    public String getThisDate(@RequestBody  @RequestParam("data")Date date) {//获取这个 @RequestBody 时间类型不行




//        String 类型转localDate
//        LocalDate beginDateTime = LocalDate.parse(beginDate, DateTimeFormatter.ofPattern(“yyyy-MM-dd”));
//
//        date类型转localDate
//        Date dates = new SimpleDateFormat(“yyyyMM”).parse(cycle);
//        Instant instant = dates.toInstant();
//        ZoneId zoneId = ZoneId.systemDefault();
//        LocalDate localDate = instant.atZone(zoneId).toLocalDate();


//        return "success";
        LocalDate DateTime = LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        return DateTime.toString();
        return dayService.getByDate(DateTime);
//        return dayService.getByDate(date);
//        return date.toString();

    }


    @RequestMapping("/allId")
    public String getAll(){
        return dayService.getAll();
    }


    @RequestMapping("/getTpStart")
    public String getBySe(String start){

        LocalDateTime localDateTime = LocalDateTimeUtil.parse(start,DatePattern.NORM_DATETIME_PATTERN);
//        System.out.println("  LocalDateTimeUtil 获取"+localDateTime);
        return JSON.toJSONString( dayService.getDayBySE(localDateTime.withNano(0)));//舍去毫秒数 改为6位格式
//        return         dayService.getDayBySE(localDateTime.withNano(0));//舍去毫秒数 改为6位格式
    }


    @RequestMapping("/addTp")
//  did没传  public void addDay(String did ,String start,String end,String feel,String text,String tid){
    public String  addDay(String start,String end,String feel,String text,String tid){
        System.out.println("添加 day");
            HashMap<String, Object> map = new HashMap<String, Object>();
//        map.put("did", IdUtil.fastSimpleUUID());   /*IdUtil.fastSimpleUUID() 返回String类型  */
        map.put("did",null);//uuid 是字符串 要改 xml , controll, 数据库 ，前端判断 要改
        //传类型
        map.put("tid",Integer.parseInt(tid));

        map.put("feel",feel);
        map.put("text",text);
        map.put("start",LocalDateTimeUtil.parse(start,DatePattern.NORM_DATETIME_PATTERN));
        map.put("end",LocalDateTimeUtil.parse(end,DatePattern.NORM_DATETIME_PATTERN));

//        userDao.addUser2(map);


        Day d = new Day();

        d.setText(text);
        d.setFeel(feel);
        d.setStart(LocalDateTimeUtil.parse(start,DatePattern.NORM_DATETIME_PATTERN));
        d.setEnd(LocalDateTimeUtil.parse(end,DatePattern.NORM_DATETIME_PATTERN));

        mailServer.addMail(d);


//        LocalDateTime localDateTime = LocalDateTimeUtil.parse(start);
//        System.out.println("  LocalDateTimeUtil 获取"+localDateTime);
        dayService.add(map);
        return "TRUE";
    }

    @RequestMapping("/updateTp")
    public  String  update(String did ,String start,String end,String feel,String text,String tid){
//    public  String  update(@RequestBody Day day){
        Day day= new Day();
        day.setDid(Integer.valueOf(did.trim()));
        day.setTid(Integer.valueOf(tid.trim()));
        day.setStart(LocalDateTimeUtil.parse(start,DatePattern.NORM_DATETIME_PATTERN));
        day.setEnd(LocalDateTimeUtil.parse(end,DatePattern.NORM_DATETIME_PATTERN));
        day.setText(text);
        day.setFeel(feel);






        mailServer.changeMail(day);





//        String type ="";
//        String cron="";
//        jobsConfigTest.update(type, cron);

        dayService.update(day);
        return "TRUE";
    }


    @RequestMapping("/delTpStart")
    public String delbyse(String start){
        if (start==null){
            System.out.println("无参数时间");
        }

//        String type ="";
//        jobsConfigTest.cancel(type);

        LocalDateTime localDateTime = LocalDateTimeUtil.parse(start,DatePattern.NORM_DATETIME_PATTERN);
//        System.out.println("  LocalDateTimeUtil 获取 =》del"+localDateTime);




        Day d = new Day();
        d.setStart(LocalDateTimeUtil.parse(start,DatePattern.NORM_DATETIME_PATTERN));
        mailServer.deleteMail(d);



        dayService.deleteByStart(localDateTime.withNano(0));

        return "TRUE";
    }






}
