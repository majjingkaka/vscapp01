package com.example.vscapp01.controller;

import java.nio.charset.Charset;

//import java.util.List;
//import java.util.Map;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vscapp01.Components.JwtTokenProvider;
import com.example.vscapp01.dto.MemberDto;
import com.example.vscapp01.dto.MemberLoginRequestDto;
//import com.example.vscapp01.config.JwtTokenInfo;
//import com.example.vscapp01.dto.MemberDto;
//import com.example.vscapp01.dto.MemberLoginRequestDto;
import com.example.vscapp01.entity.MemberEntity;
import com.example.vscapp01.repository.MemberRepository;
import com.example.vscapp01.service.MemberService;

@RestController
@RequestMapping(value = "/api")
public class MemberApiController {
	//private static final Logger LOGGER = LogManager.getLogger(MemberApiController.class);
    
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    //private JwtTokenProvider jwtTokenProvider;

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

    // @PostMapping("/login")
    // public JwtTokenInfo login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
    //     String memberId = memberLoginRequestDto.getMemberId();
    //     String password = memberLoginRequestDto.getPassword();
    //     JwtTokenInfo jwtTokenInfo = memberService.login(memberId, password);
    //     return jwtTokenInfo;
    // }

    //로그인
    //@PostMapping("/login")
    @RequestMapping(value = "/login" , method = {RequestMethod.POST})
    public ResponseEntity<String> login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        
        //final List<MemberEntity> userList = memberMapper.findAll();
        //if (userList.isEmpty())
            //return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER);
        //return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_USER, userList);
        //https://thalals.tistory.com/268
        
        //log.info("user email = {}", user.get("email"));
        MemberEntity MemberEntity = memberRepository.findByMemberId(memberLoginRequestDto.getMemberId());
                //.orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        String tokn = memberService.createToken(MemberEntity);
        //jwtTokenProvider.createToken(MemberEntity.getMemberId(), MemberEntity.getRoles())
        return new ResponseEntity<String>(tokn, header, HttpStatus.OK);
    }
    
    @PostMapping("/test")
    public String test(){

        return "<h1>test 통과</h1>";
    }

    
}
