package com.ycj.mall;

import com.ycj.mall.entity.User;
import com.ycj.mall.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootRedisTests {

    @Autowired
    private RedisUtil redisTemplate;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setUsername("ycj");
        user.setPassword("123456");
        redisTemplate.set("user", user);
        System.out.println(redisTemplate.get("user"));
    }
}