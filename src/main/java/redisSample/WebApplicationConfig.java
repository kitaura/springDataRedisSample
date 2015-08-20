package redisSample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@ComponentScan(basePackages="redisSample")
@EnableWebMvc
public class WebApplicationConfig extends WebMvcConfigurerAdapter {
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory cf = new JedisConnectionFactory();
        cf.setUsePool(true);
        cf.setHostName("localhost");
        cf.setPort(6379);
        return cf;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> rt = new RedisTemplate<String, Object>();
        rt.setConnectionFactory(jedisConnectionFactory());
        rt.setKeySerializer(new StringRedisSerializer());
        rt.setValueSerializer(new JacksonJsonRedisSerializer<>(Object.class));

        return rt;
    }
}
