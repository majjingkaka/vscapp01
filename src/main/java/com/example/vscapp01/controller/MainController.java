package com.example.vscapp01.controller;


//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.example.vscapp01.config.JwtTokenInfo;
import com.example.vscapp01.dto.MemberDto;
import com.example.vscapp01.dto.MemberLoginRequestDto;
import com.example.vscapp01.service.MemberService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping(value = "/")
//@AllArgsConstructor
public class MainController{
	//private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MemberService memberService;

    //https://velog.io/@juan003/2019-01-25-1001-%EC%9E%91%EC%84%B1%EB%90%A8-9djrbeb13c
    //@RequestMapping(value={"", "/", "/error", "/*"}, method = RequestMethod.GET)
    //public String index() {
    //    return "index.html";
    //}
    
    // @RequestMapping("/") 
    // public String logIn() {
	// 	System.out.println("/1");
		
    //     return "index.html";
    // }

    /**
     * localhost:8080 시 logIn 으로 redirect
     * @return
     */
    @GetMapping
    public String root() {
        return "redirect:/logIn";
    }

    /**
     * 로그인 폼
     * @return
     */
    @GetMapping("/logIn")
    public String logIn(){
        logger.trace("Trace");
        logger.debug("Debug");
        logger.info("Info");
        logger.warn("Warn");
        logger.error("Error");

        return "index.html";
    }
    
    @GetMapping("/signUp")
    public String signupView() {
        return "signUp";
    }
    
    @RequestMapping(path = "/signUp", produces = "application/json", method= RequestMethod.POST)
    public String signup(MemberDto memberDto) throws Exception {
        memberService.createMember(memberDto);
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }


















	@RequestMapping("/vue") 
    public String vue() {
		System.out.println("vue2");
		
        return "index.html";
    }

	@RequestMapping("/api") 
    public String api() {
		System.out.println("api");
		
        return "api3"; 
    }  
	

    
    
}
