package com.liang.thymeleaf.service.impl;

import com.liang.thymeleaf.dao.UserDao;
import com.liang.thymeleaf.domain.User;
import com.liang.thymeleaf.imag.GraphicHelper;
import com.liang.thymeleaf.service.LoginService;
import com.liang.thymeleaf.exception.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;


@Service
public class LoginServiceImpl implements LoginService {


    @Resource
    private UserDao dao;

    @Override
    public User login(String username, String password, String codeImage) throws LoginException {
        User user;
        if (!codeImage.equals(GraphicHelper.getCaptcha())){//不使用session来进行验证码的验证
            throw new LoginException("验证码错误");
        }
        user= dao.selectUser(username, password);
        if (user==null){
            throw new LoginException("用户名或密码错误");
        }
        return user;
    }
}
