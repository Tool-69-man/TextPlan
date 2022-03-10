package com.xroad.controller;

import com.alibaba.fastjson.JSON;
import com.xroad.entity.Pie;
import com.xroad.service.PieService;
import com.xroad.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/pie")
@CrossOrigin
public class PieController {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    PieService pieService;

    @RequestMapping("/pie")
    public  String getPie(String nowdate){

        System.out.println("传过来的日期"+nowdate);

//数据库 传过来的 要 JSON.toJSONString(pieList)
//        List<Pie> pieList = pieService.toLookNowDate(nowdate);
//        System.out.println(pieList);
//        return  JSON.toJSONString(pieList);

        return  JSON.toJSONString(pieService.toLookNowDate(nowdate));

//            return  pieService.toLookNowDate(nowdate);

    }

    @RequestMapping("/piea")
    public  String getPiea(){

        String str =  "[\n" +
                "  {\"value\":\"12\" , \"name\":\"eataaaaaaaaaaaaaaaaa\"},\n" +
                "  {\"value\":\"62\" , \"name\":\"sleep\"},\n" +
                "  {\"value\":\"122\" , \"name\":\"study\"},\n" +
                "  {\"value\":\"99\" , \"name\":\"stq\"}\n" +
                "]";

        System.out.println("3个饼图 同时请求  pieA");
        return str.toString();
    }

    @RequestMapping("/pieb")
    public  String getPieb(){

        String str =  "[\n" +
                "  {\"value\":\"12\" , \"name\":\"eat\"},\n" +
                "  {\"value\":\"62\" , \"name\":\"XXXXXX\"},\n" +
                "  {\"value\":\"122\" , \"name\":\"studyaaaaaaaaaaaaaaaaaaaaaaaa\"},\n" +
                "  {\"value\":\"99\" , \"name\":\"stqweqweqeudy2\"}\n" +
                "]";

        return str;
    }


}
