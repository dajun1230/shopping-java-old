package com.ycj.mall.security;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ycj.mall.common.exception.CaptchaException;
import com.ycj.mall.common.lang.Const;
import com.ycj.mall.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
// getReader() has already been called for this request
public class CaptchaFilter {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    LoginFailureHandler loginFailureHandler;

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String url = request.getRequestURI();
//
//        if ("/api/users/login".equals(url) && request.getMethod().equals("POST")) {
//            try {
//                // 校验验证码
//                validate(request);
//                // 如果不正确，就跳转到认证失败处理器
//            } catch (CaptchaException e) {
//                // 交给认证失败处理器
//                loginFailureHandler.onAuthenticationFailure(request, response, e);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }

    // 校验验证码逻辑
    public void validate(HttpServletRequest request) throws IOException {
        System.out.println("request============" + request);
        String str = getBodyText(request);
        System.out.println("str ===" + str);
        JSONObject jsonObject = JSONUtil.parseObj(str);
        String code = jsonObject.getStr("code");
        String key = jsonObject.getStr("token");
        System.out.println("新token" + key);
        System.out.println("新code" + code);
        System.out.println("原有code" + redisUtil.hget(Const.CAPTCHA_KEY, key));

        if (StringUtils.isBlank(code) || StringUtils.isBlank(key)) {
            throw new CaptchaException("验证码错误");
        }

        if (!code.equals(redisUtil.hget(Const.CAPTCHA_KEY, key))) {
            throw new CaptchaException("验证码错误");
        }

        // 一次性使用
        redisUtil.hdel(Const.CAPTCHA_KEY, key);
    }

    public String getBodyText(HttpServletRequest request) throws IOException {
        BufferedReader br = request.getReader();
        String str, wholeStr = "";
        while((str = br.readLine()) != null){
            wholeStr += str;
        }
        return wholeStr;
    }

}
