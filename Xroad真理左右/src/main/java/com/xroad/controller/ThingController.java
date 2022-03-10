package com.xroad.controller;


import cn.hutool.core.util.IdUtil;
import com.xroad.entity.Thing;
import com.xroad.service.ThingService;
import com.xroad.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
@RequestMapping("/thing")
@CrossOrigin
public class ThingController {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    ThingService thingService;

    @RequestMapping("/getThings")
    public  String getThings(){

        return  thingService.getAllThing();
    }


    @RequestMapping("/getThingById")
    public  String getThingById(int id){

        return  thingService.getThingById(id);
    }


    @RequestMapping("/getThingByName")
    public  String getThingByName(String  name){

        return  thingService.getThingByName(name);
    }
    @RequestMapping("/addThing")
    public void add(String name , String allFeel ){


        HashMap<String, Object> map = new HashMap<String, Object>();



//        map.put("tid",IdUtil.fastSimpleUUID());
        map.put("tid",null);//为了  things能自动增值
        //传类型
        map.put("name",name);
        map.put("allFeel",allFeel);

        thingService.add(map);
    }


    @RequestMapping("/updateThing")
    public void updateThing(String tid,String name,String allFeel){
        //这里可以做判断

        Thing thing = new Thing();
        thing.setTid(Integer.valueOf(tid.trim()));
        thing.setName(name);
        thing.setAllFeel(allFeel);

        thingService.updateThing(thing);

    }

    @RequestMapping("/deleteThing")
    public void deleteThingById(int tid){
        //这里可以做判断
        thingService.deleteThing(tid);

    }

    @RequestMapping("/deleteThingByName")
    public void deleteThingByName(String name){
        //这里可以做判断
        thingService.deleteThingByName(name);

    }





}
