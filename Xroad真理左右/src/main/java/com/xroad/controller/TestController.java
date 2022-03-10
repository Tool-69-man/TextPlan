package com.xroad.controller;

import com.xroad.common.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin
public class TestController {
    @GetMapping("/wt")
    public Result withToken() {
        log.info("### 必须带token请求 ###");
        return Result.SUCCESS();
    }
}
