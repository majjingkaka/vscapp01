package com.example.vscapp01.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//https://gilu-world.tistory.com/107 [Spring + Vue.js] 새로고침 에러
@Controller
public class CustomErrorController implements ErrorController{
    private final String ERROR_PATH = "/error";

    @GetMapping(ERROR_PATH)
    public String redirectRoot(){
        return "index.html";
    }

    public String getErrorPath(){
        return null;
    }
}
