package com.ycj.mall.service.impl;

import com.ycj.mall.entity.User;
import com.ycj.mall.mapper.UserMapper;
import com.ycj.mall.service.IUserService;
import com.ycj.mall.service.ex.InsertException;
import com.ycj.mall.service.ex.UsernameDuplicatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

// 用户模块业务层的实现类
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void registerUser(User user) {
        // 校验用户名重复问题
        String username = user.getUsername();
        User result = userMapper.findByUsername(username);
        if (result != null) {
            throw new UsernameDuplicatedException("用户名被占用");
        }
        // 密码加密处理：md5算法的形式
        // 串 + password + 串 --- md5算法进行加密，连续加载三次
        // 盐值 + password + 盐值 --- 盐值就是一个随机的字符串
        String password = user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();
        String newPassword = getMD5Password(password, salt);
        user.setPassword(newPassword);
        user.setSalt(salt);
        // 补全数据
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        // 注册
        Integer rows = userMapper.createUser(user);
        if (rows != 1) {
            throw new InsertException("在用户注册过程中产生了未知的异常");
        }
    }

    @Override
    public User login(String username, String password) {
        User result = userMapper.findByUsername(username);
        if (result == null) {
            throw new UsernameDuplicatedException("用户数据不存在");
        }
        // 监测用户的密码是否匹配
        // 1. 先获取到数据库中的加密之后的密码
        String oldPassword = result.getPassword();
        // 2. 和用户的传递过来的密码进行比较
        // 2.1 先获取盐值：上一次在注册时所自动生成的盐值
        String salt = result.getSalt();
        // 2.2 将用户的密码按照相同的md5算法的规则进行加密
        String newPassword = getMD5Password(password, salt);
        // 3. 将密码进行比较
        if (!newPassword.equals(oldPassword)) {
            throw new UsernameDuplicatedException("用户密码错误");
        }
        // 判断is_delete字段的值是否为1表示被标记为删除
        if (result.getIsDelete() == 1) {
            throw new UsernameDuplicatedException("用户数据不存在");
        }

        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setEmail(result.getEmail());
        user.setAvatar(result.getAvatar());

        return user;
    }

    //    定义一个md5算法的加密处理
    private String getMD5Password(String password, String salt) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
