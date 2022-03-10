package com.xroad.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;

import java.nio.charset.Charset;
import java.util.Map;

@ConfigurationProperties(
        prefix = "spring.mail"
)
public class MailInfo {
//    private static final Charset DEFAULT_CHARSET ;
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String protocol = "smtp";
    private Charset defaultEncoding;
    private Map<String, String> properties;
    private String jndiName;






}
