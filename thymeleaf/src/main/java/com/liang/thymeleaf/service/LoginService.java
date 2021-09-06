package com.liang.thymeleaf.service;

import com.liang.thymeleaf.domain.User;
import com.liang.thymeleaf.exception.LoginException;

public interface LoginService {
    User login(String username, String password, String codeImage) throws LoginException;
}
