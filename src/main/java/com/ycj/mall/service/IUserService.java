package com.ycj.mall.service;

import com.ycj.mall.entity.User;

// 用户模块业务吃接口
public interface IUserService {

    /**
     * 用户注册方法
     * @param user
     */
    void registerUser(User user);
}
