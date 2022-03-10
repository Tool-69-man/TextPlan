package com.xroad.controller;

import com.xroad.annotation.JwtIgnore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Slf4j
@Controller
@RequestMapping("/check")
public class Check {
//    @RequestMapping("/beforeDel") //是否 删除时是否 需要（取决于 去删除空的值是否报错  ）

    @JwtIgnore
    @RequestMapping(value = "/swagger")
    public String swagger() {
        System.out.println("swagger-ui.html");
        return "redirect:swagger-ui.html";
    }



    @RequestMapping(value = "/druid")
    public String druid() {
//        System.out.println("swagger-ui.html");
        return "redirect:druid";
    }
}
