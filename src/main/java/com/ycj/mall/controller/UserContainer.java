package com.ycj.mall.controller;

import com.ycj.mall.entity.User;
import com.ycj.mall.service.IUserService;
import com.ycj.mall.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
//    @CrossOrigin()
    public JsonResult<Void> register(User user) {
        iUserService.registerUser(user);
        return new JsonResult<>(OK);
    }

    @PostMapping("login")
    public JsonResult<User> login(String username, String password) {
        User data = iUserService.login(username, password);
        return new JsonResult<User>(OK, data);
    }

}
