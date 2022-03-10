package com.xroad.service;

import com.xroad.dao.ThingDao;
import com.xroad.entity.Thing;
import com.xroad.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ThingService {

    @Autowired
    ThingDao thingDao;

    @Autowired
    RedisUtil redisUtil;

    public String getAllThing(){


        if (redisUtil.hasKey("Things")){
            System.out.println("redis 有 Things");
        }else {
            System.out.println("缓存没有,数据库放入Things");
            redisUtil.lSet("Things",thingDao.getAllThing(),3600);

        }

        System.out.println("数据库获取Things"+thingDao.getAllThing());
        System.out.println("redis获取Things"+redisUtil.lGet("Things",0,-1));

        return redisUtil.lGet("Things",0,-1).toString();

        }

    public String getThingById(int id){



        return String.valueOf(thingDao.getThingById(id));

    }

    public String getThingByName(String name){

        if (redisUtil.hasKey(name)){
            System.out.println(name);
            return redisUtil.get(name).toString();
        }else {
            System.out.println("缓存没有,数据库放入"+name);

             Thing thing = thingDao.getThingByName(name);
//            System.out.println(day);
            if (thing!=null){//数据库有这条数据 才放入redis
                redisUtil.set(name,thing,3600);
                return redisUtil.get(name).toString();
            }

//            day.getStart().toLocalDate();
//            day.getStart().getHour();
//            day.getStart().getMinute();
//            day.getStart().getSecond();



        }


        return "FLASE";
    }

    public void add(Map<String,Object> map){
        //这里可以做判断
        thingDao.add(map);
        String name = (String) map.get("name");
        Integer tid = (Integer) map.get("tid");
        String allFeel = (String) map.get("allFeel");


        Thing thing = new Thing();
        thing.setTid(tid);
        thing.setName(name);
        thing.setAllFeel(allFeel);


        redisUtil.lSet("Things",thingDao.getAllThing(),3600);
        redisUtil.set(name,thing,3600);

    }

    public void updateThing(Thing thing){
        //这里可以做判断
        thingDao.updateThing(thing);
        redisUtil.set(thing.getName(),thing,3600);
        redisUtil.lSet("Things",thingDao.getAllThing(),3600);

    }
    public void deleteThing(int tid){
        //这里可以做判断
        thingDao.deleteThing(tid);

    }
    public void deleteThingByName(String name){
        //这里可以做判断



        thingDao.deleteThingByName(name);


        if (redisUtil.hasKey(name)){
            System.out.println("缓存删除Thing => "+name);
            redisUtil.del(name);
        }


        redisUtil.lSet("Things",thingDao.getAllThing(),3600);





    }

}
