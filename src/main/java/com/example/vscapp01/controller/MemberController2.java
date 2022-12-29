package com.example.vscapp01.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vscapp01.entity.MemberEntity;
import com.example.vscapp01.service.MemberService;

@RestController
@RequestMapping(value = "/api")
public class MemberController2 {
	private static final Logger LOGGER = LogManager.getLogger(MemberController2.class);
    
    @Autowired
    MemberService memberService;


    //https://ocblog.tistory.com/49
    //https://code-lab1.tistory.com/259
    //https://eastflag.co.kr/fullstack/rest-with-spring/spring-rest_get_post/
    //@PostMapping("/memberreg")
    @RequestMapping(value = "/memberreg" , method = {RequestMethod.GET, RequestMethod.POST})
    public MemberEntity memberreg(//HttpServletRequest request, HttpServletResponse response
                            //@RequestParam(value = "id", required=false) String id, 
                            //@RequestParam(value = "email", required=false) String email, 
                            //@RequestParam(value = "password", required=false) String password
                            @RequestBody MemberEntity memberEntity
                            ) {
        return memberEntity;
    }

	// @RequestMapping(value = "/select01", method=RequestMethod.GET)
	// public List<MemberEntity> select01(HttpServletRequest request) throws Exception {
	// 	//ModelAndView mav = new ModelAndView();
		
	// 	LOGGER.debug("Hello World");
    //     LOGGER.info("Hello World");
    //     LOGGER.warn("Hello World");
        
	// 	//List<MemberEntity> memberList = memberService.getFindAll();
		
	// 	//mav.addObject("salaryList", salaryList);
	// 	//mav.setViewName("content/home.html");
		
	// 	return memberList;
	// }
}
