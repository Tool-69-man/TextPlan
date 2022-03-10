package com.xroad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;



import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(Environment environment) {
//            public Docket createRestApi(Environment environment) {
//        org.springframework.core.env.Environment   获取当前环境

        Profiles myProfiles =Profiles.of("dev","test");//不定参数  设置环境对象
        boolean flag = environment.acceptsProfiles(myProfiles);//判断 是否所处myProfiles环境

        //可以用flag 判断 开发环境和发布环境
        //用来操控 用户是否进入
//            .enable(false)  不给进入

        return new Docket(DocumentationType.SWAGGER_2)
//                .host("http://localhost:8080/")
                .apiInfo(apiInfo())
                .enable(true)
                .select()// 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
//                .apis(RequestHandlerSelectors.basePackage("com.myswagger.controllerTest"))
                .paths(PathSelectors.any())
                .build();
    }
    //
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Time Plans")
                .description("草稿纸：计划表，日记，分析自己")
//                .termsOfServiceUrl("http://192.168.1.198:10070/platformgroup/ms")
//                .contact("程序猿")
                .version("1.0")
                .build();
    }

}
//   .apis(RequestHandlerSelectors.any())
//  any() // 扫描所有，项目中的所有接口都会被扫描到
//    none() // 不扫描接口

//    // 通过方法上的注解扫描，如withMethodAnnotation(GetMapping.class)只扫描get请求
//    withMethodAnnotation(final Class<? extends Annotation> annotation)

//    // 通过类上的注解扫描，如.withClassAnnotation(Controller.class)只扫描有controller注解的类中的接口
//    withClassAnnotation(final Class<? extends Annotation> annotation)


//    basePackage(final String basePackage) // 根据包路径扫描接口