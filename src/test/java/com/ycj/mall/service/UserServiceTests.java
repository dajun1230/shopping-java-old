package com.ycj.mall.service;

import com.ycj.mall.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.rowset.serial.SerialException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private IUserService iUserService;

    @Test
    public void registerUser() {
//        try {
            User user = new User();
            user.setUsername("xiaoyang01");
            user.setPassword("123");
            iUserService.registerUser(user);
            System.out.println("OK");
//        } catch (SerialException e) {
//            System.out.println(e.getClass().getSimpleName());
//            System.out.println(e.getMessage());
//        }
    }
}
