package com.ycj.mall.controller;

import com.ycj.mall.entity.User;
import com.ycj.mall.service.IUserService;
import com.ycj.mall.service.ex.InsertException;
import com.ycj.mall.service.ex.UsernameDuplicatedException;
import com.ycj.mall.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController // 组合注解： @Container + @RequestBody
@RequestMapping("users")
public class UserContainer extends BaseController {
    @Autowired
    private IUserService iUserService;
    
//    @RequestMapping("register")
//    @RequestBody 表示此方法的响应结果以json格式进行数据的响应给到前端
//    public JsonResult<Void> register(User user) {
//        JsonResult<Void> result = new JsonResult<>();
//        try {
//            iUserService.registerUser(user);
//            result.setState(200);
//            result.setMessage("用户注册成功");
//        } catch (UsernameDuplicatedException e) {
//            result.setState(4000);
//            result.setMessage("用户名被占用");
//        } catch (InsertException e) {
//            result.setState(5000);
//            result.setMessage("注册时产生未知的异常");
//        }
//        return result;
//    }
    @RequestMapping("register")
    public JsonResult<Void> register(User user) {
        iUserService.registerUser(user);
        return new JsonResult<>(OK);
    }
}
