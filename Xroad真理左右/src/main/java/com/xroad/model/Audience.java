package com.xroad.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description 配置信息DTO，通过读取配置文件自动赋值。
 * @ClassName: Audience
 * @author: 郭秀志 jbcode@126.com
 * @date: 2020/7/16 20:07    
 * @Copyright:  
 */
@Data
@ConfigurationProperties(prefix = "audience")
@Component
public class Audience {
    //代表这个JWT的接收对象,存入audience
    private String aud;
    private String base64Secret;
    //JWT的签发主体，存入issuer
    private String iss;
    private int expiresSecond;

}
