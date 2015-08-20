package redisSample;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
public class SampleController {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    public void getData(@RequestParam("key") String key, HttpServletResponse res) throws IOException {
        res.getWriter().write(redisTemplate.opsForValue().get(key).toString());
    }

    @RequestMapping(value = "/setData", method = RequestMethod.GET)
    public void setData(@RequestParam("key") String key,@RequestParam("value") String value, HttpServletResponse res) throws IOException {
        SampleDto dto = new SampleDto();
        dto.value = value;
        dto.creationDateTime = System.currentTimeMillis();
        redisTemplate.opsForValue().set(key, dto);
        res.getWriter().write("OK");
    }
}
