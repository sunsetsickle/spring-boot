package com.liang.thymeleaf.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping(value = "some.do")

    public String doSome(Model model){
        model.addAttribute("data",Thread.currentThread().getName());
        return "test";
    }
}
