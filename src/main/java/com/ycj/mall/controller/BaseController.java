package com.ycj.mall.controller;

import com.ycj.mall.service.ex.InsertException;
import com.ycj.mall.service.ex.ServiceException;
import com.ycj.mall.service.ex.UsernameDuplicatedException;
import com.ycj.mall.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 控制类的基类
public class BaseController {
    public static final int OK = 200;

    // 请求处理方法，这个方法的返回值就是需要传递给前端的数据
    // 自动将异常对象传递给此方法的参数列表上
    // 当前项目中产生了异常，被统一拦截到此方法中，这个方法此时就充当的是请求处理方法，方法的返回值直接给到前端
    @ExceptionHandler(ServiceException.class) // 用于统一处理抛出的异常
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>();
        if (e instanceof UsernameDuplicatedException) {
            result.setState(4000);
            result.setMessage("用户名被占用");
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("注册时产生未知的异常");
        }
        return result;
    }
}
