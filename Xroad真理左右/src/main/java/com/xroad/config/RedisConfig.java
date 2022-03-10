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
    @SuppressWarnings("all")    //告诉编译器忽略指定的警告，不用在编译完成后出现警告信息
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        //为了自己开发方便,使用String，Object类型
        RedisTemplate<Object, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        //序列化配置，使用json解析任意的对象，将对象解析成可以序列化的对象
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        FastJsonRedisSerializer fastJsonRedisSerializer= new FastJsonRedisSerializer(Object.class);
        //使用Mapper对象进行转义
        ObjectMapper om = new ObjectMapper();

//        创建并替换一致的序列化类
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        byte[] values = jackson2JsonRedisSerializer.serialize(value);
//        setSerializer(template);

        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        //开始序列化对象
        jackson2JsonRedisSerializer.setObjectMapper(om);
        //String 类型的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //key采用String序列化的方式
        template.setKeySerializer(stringRedisSerializer);
        //hash采用String序列化的方式
        template.setHashKeySerializer(stringRedisSerializer);
        //value序列化方式采用jackson
//        template.setValueSerializer(jackson2JsonRedisSerializer);// jackjson 会转换这个
        template.setValueSerializer(fastJsonRedisSerializer);//不会转换 localDateTime
        //hash的value序列化方式采用jackson
//        template.setHashKeySerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(fastJsonRedisSerializer);
        template.afterPropertiesSet();

//        setSerializer(template);//用fastJson 转json  方法2


        //这里放入时 都能  存入 但 取出时报错  ， 用jackjson 放入 ，能用fastjson 获取

        return template;
    }
//
//    @Bean
//    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory)
//            throws UnknownHostException {
//        System.out.println("=========怎么没进入stringRedisTemplate");
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
        defaultCacheConfig = defaultCacheConfig.entryTtl(Duration.ofSeconds(100));//设置过期时间
        RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
        ParserConfig.getGlobalInstance().addAccept("zmc.leon.mcd.entity.");
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        return cacheManager;
    }


//    /**
//     * 利用fastjson将转为json字符串
//     * @param template
//     */

    private void setSerializer(RedisTemplate<Object, Object> template) {
        @SuppressWarnings({"rawtypes", "unchecked"})
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer =new FastJsonRedisSerializer<Object>(Object.class);
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);
        // key的序列化采用StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
    }
//    /**
////     * 自定义生成key的规则
////     *
////     * @return
////     */
//    @Override
//    public KeyGenerator keyGenerator() {
//        return new KeyGenerator() {
//            @Override
//            public Object generate(Object o, Method method, Object... objects) {
//                //格式化缓存key字符串
//                StringBuilder sb = new StringBuilder();
//                //追加类名
//                sb.append(o.getClass().getName()).append(".");
//                //追加方法名
//                sb.append(method.getName());
//                //遍历参数并且追加
//                for (Object obj : objects) {
//                    sb.append(".");
//                    sb.append(obj.toString());
//                }
//                System.out.println("调用Redis缓存Key : " + sb.toString());
//                return sb.toString();
//            }
//        };
//    }


}
