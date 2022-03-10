package com.xroad.service;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.xroad.dao.DayDao;
import com.xroad.entity.Day;
import com.xroad.entity.Pie;
import com.xroad.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PieService {


    @Autowired
    RedisUtil redisUtil;

    @Autowired
    DayDao dayDao;



    //数据库获取

    //pie  name 日期   所有所有数据   value  时间
    public List<Object> toLookNowDate(String date){


//         date dayDao.getByDate( LocalDate.parse(date)) 获取 当天的day
        List<Day> byDate = dayDao.getByDate(LocalDate.parse(date));//获取 当天的day

        List<Pie> pieList= new ArrayList<>();

        Pie pie = null;
        for (Day d :byDate) {
           pie = new Pie();
            //名字 text
            //获取 Thing name 如果 tid==1 就 放入临时自定义事件
            if (d.getThing().getTid()==1){
                pie.setName(d.getText());  //临时
            }else {
                pie.setName(d.getThing().getName()+"*");//定好的事件
            }

            //value  start - end  的值  转成long吧

            Duration between = LocalDateTimeUtil.between(d.getStart(), d.getEnd());
            //毫秒数
            Long betweenM = between.toMillis();

            pie.setValue(String.valueOf(betweenM));
            //添加 pie
            pieList.add(pie);
        }


        //redis  存入  pie 数组


        if (redisUtil.hasKey("pie")){
            System.out.println("pie");
        }else {
            System.out.println("缓存没有,数据库放入pie");
            redisUtil.lSet("pie",pieList,3600);
        }


        System.out.println(pieList.toString());
        System.out.println("redis获取"+redisUtil.lGet("pie",0,-1));

        return redisUtil.lGet("pie",0,-1);

//        return  pieList;

    }



    //获取过去的同一事件



}
