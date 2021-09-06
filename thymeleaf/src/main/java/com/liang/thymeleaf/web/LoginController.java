package com.liang.thymeleaf.web;
import com.liang.thymeleaf.domain.User;
import com.liang.thymeleaf.imag.GraphicHelper;
import com.liang.thymeleaf.service.LoginService;
import com.liang.thymeleaf.util.MD5Util;
import com.liang.thymeleaf.util.PrintJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Controller
public class LoginController {

    @Resource
    private LoginService service;

    @RequestMapping(value = "code.do")
    public void codeImage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        GraphicHelper.create(response);//生成验证码
    }

    @RequestMapping(value = "login.do")
    public void login(String loginAct, String loginPwd, String codeImage, HttpServletResponse response, HttpServletRequest request) {
        loginPwd = MD5Util.getMD5(loginPwd);
        try {
            User user = service.login(loginAct, loginPwd,codeImage);
            request.getSession().setAttribute("user", user);
            PrintJson.printJsonFlag(response, true);
        } catch (Exception e) {
            e.printStackTrace();
            String msg = e.getMessage();
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("msg", msg);
            PrintJson.printJsonObj(response, map);//把map打包成json 并发送给前端
        }
    }
}
