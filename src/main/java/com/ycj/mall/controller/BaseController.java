package com.ycj.mall.controller;

import com.ycj.mall.service.ex.InsertException;
import com.ycj.mall.service.ex.ServiceException;
import com.ycj.mall.service.ex.UsernameDuplicatedException;
import com.ycj.mall.common.lang.Result;
import com.ycj.mall.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 控制类的基类
public class BaseController {

    @Autowired
    RedisUtil redisUtil;

    public static final int OK = 0;

    // 请求处理方法，这个方法的返回值就是需要传递给前端的数据
    // 自动将异常对象传递给此方法的参数列表上
    // 当前项目中产生了异常，被统一拦截到此方法中，这个方法此时就充当的是请求处理方法，方法的返回值直接给到前端
    @ExceptionHandler(ServiceException.class) // 用于统一处理抛出的异常
    public Result<Void> handleException(Throwable e) {
        Result<Void> result = new Result<>();
        if (e instanceof UsernameDuplicatedException) {
            result.setCode(4000);
            result.setMsg("用户名被占用");
        } else if (e instanceof InsertException) {
            result.setCode(5000);
            result.setMsg("注册时产生未知的异常");
        }
        return result;
    }
}
