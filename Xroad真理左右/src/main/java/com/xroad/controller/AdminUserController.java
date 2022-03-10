package com.xroad.controller;

import com.alibaba.fastjson.JSONObject;
import com.xroad.annotation.JwtIgnore;
import com.xroad.common.response.Result;
import com.xroad.model.Audience;
import com.xroad.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin
public class AdminUserController {

    @Autowired
    private Audience audience;

//
    String tokenCopy;


//    @RequestMapping("/login")
    @PostMapping("/login")
    @JwtIgnore
    @CrossOrigin
    public Result adminLogin(HttpServletResponse response, String username, String password) {
        // 这里模拟测试, 默认登录成功，返回用户ID和角色信息
        String userId = UUID.randomUUID().toString();
        String role = "admin";
        System.out.println("进入adminlogin");






        // 创建token
        String token = JwtTokenUtil.createJWT(userId, username, role, audience);
        log.info("### 登录成功, token={} ###", token);
        tokenCopy=token;
        // 将token放在响应头

//        response.setHeader("Access-Control-Expose-Headers","Content-Disposition");//指定Content-Disposition可以让前端获取
        response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, JwtTokenUtil.TOKEN_PREFIX + token);
        // 将token响应给客户端
        JSONObject result = new JSONObject();
        result.put("token", token);
        return Result.SUCCESS(result);
    }
//

//    @RequestMapping(value = "/swagger")
//    @JwtIgnore
//    @CrossOrigin
//    public void swagger(HttpServletResponse response, HttpServletRequest request) {
//
//        System.out.println("swagger-ui.html");
//        try {
//            response.sendRedirect("swagger-ui.html");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        request.setAttribute("flag","swagger");
//
//    }
}
