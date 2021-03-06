package com.xroad.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;
import java.net.UnknownHostException;
import java.sql.SQLOutput;
import java.time.Duration;


//@SuppressWarnings("rawtypes") private @Nullable RedisSerializer keySerializer = null;
//	@SuppressWarnings("rawtypes") private @Nullable RedisSerializer valueSerializer = null;
//	@SuppressWarnings("rawtypes") private @Nullable RedisSerializer hashKeySerializer = null;
//	@SuppressWarnings("rawtypes") private @Nullable RedisSerializer hashValueSerializer = null;


@Configuration
public class RedisConfig {

//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
//            throws UnknownHostException {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//
//        Jackson2JsonRedisSerializer<Object> objectJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>();
////        Jackson2JsonRedisSerializer
////                JdkSerializationRedisSerializer
////        OxmSerializer
////                StringRedisSerializer
////        GenericToStringRedisSerializer
////                GenericJackson2JsonRedisSerializer
//        template.setKeySerializer(objectJackson2JsonRedisSerializer);
//
//
//        template.setConnectionFactory(redisConnectionFactory);
//        return template;
//    }


    @Bean
    @SuppressWarnings("all")    //?????????????????????????????????????????????????????????????????????????????????
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        //????????????????????????,??????String???Object??????
        RedisTemplate<Object, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        //????????????????????????json??????????????????????????????????????????????????????????????????
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        FastJsonRedisSerializer fastJsonRedisSerializer= new FastJsonRedisSerializer(Object.class);
        //??????Mapper??????????????????
        ObjectMapper om = new ObjectMapper();

//        ????????????????????????????????????
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        byte[] values = jackson2JsonRedisSerializer.serialize(value);
//        setSerializer(template);

        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        //?????????????????????
        jackson2JsonRedisSerializer.setObjectMapper(om);
        //String ??????????????????
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //key??????String??????????????????
        template.setKeySerializer(stringRedisSerializer);
        //hash??????String??????????????????
        template.setHashKeySerializer(stringRedisSerializer);
        //value?????????????????????jackson
//        template.setValueSerializer(jackson2JsonRedisSerializer);// jackjson ???????????????
        template.setValueSerializer(fastJsonRedisSerializer);//???????????? localDateTime
        //hash???value?????????????????????jackson
//        template.setHashKeySerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(fastJsonRedisSerializer);
        template.afterPropertiesSet();

//        setSerializer(template);//???fastJson ???json  ??????2


        //??????????????? ??????  ?????? ??? ???????????????  ??? ???jackjson ?????? ?????????fastjson ??????

        return template;
    }
//
//    @Bean
//    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory)
//            throws UnknownHostException {
//        System.out.println("=========???????????????stringRedisTemplate");
//        StringRedisTemplate template = new StringRedisTemplate();
//        template.setConnectionFactory(redisConnectionFactory);
//        return template;
//    }


    @Bean(name="redisCacheManager")
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(factory);
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);//JSONObject
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer);
        RedisCacheConfiguration defaultCacheConfig=RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
        defaultCacheConfig = defaultCacheConfig.entryTtl(Duration.ofSeconds(100));//??????????????????
        RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
        ParserConfig.getGlobalInstance().addAccept("zmc.leon.mcd.entity.");
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        return cacheManager;
    }


//    /**
//     * ??????fastjson?????????json?????????
//     * @param template
//     */

    private void setSerializer(RedisTemplate<Object, Object> template) {
        @SuppressWarnings({"rawtypes", "unchecked"})
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer =new FastJsonRedisSerializer<Object>(Object.class);
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);
        // key??????????????????StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
    }
//    /**
////     * ???????????????key?????????
////     *
////     * @return
////     */
//    @Override
//    public KeyGenerator keyGenerator() {
//        return new KeyGenerator() {
//            @Override
//            public Object generate(Object o, Method method, Object... objects) {
//                //???????????????key?????????
//                StringBuilder sb = new StringBuilder();
//                //????????????
//                sb.append(o.getClass().getName()).append(".");
//                //???????????????
//                sb.append(method.getName());
//                //????????????????????????
//                for (Object obj : objects) {
//                    sb.append(".");
//                    sb.append(obj.toString());
//                }
//                System.out.println("??????Redis??????Key : " + sb.toString());
//                return sb.toString();
//            }
//        };
//    }


}
