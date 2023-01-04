package com.example.vscapp01.controller;

//import org.apache.catalina.security.SecurityUtil;

//import java.util.HashMap;
//import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.vscapp01.config.JwtTokenInfo;
import com.example.vscapp01.dto.MemberDto;
import com.example.vscapp01.dto.MemberLoginRequestDto;
import com.example.vscapp01.entity.MemberEntity;
//import com.example.vscapp01.entity.NewsEntity;
import com.example.vscapp01.repository.MemberRepository;
import com.example.vscapp01.service.MemberService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletResponse;

//import com.content.NewsVo;

//import com.god.bo.jpaTest.service.MemberService;
//import com.god.bo.jpaTest.vo.MemberVo;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
//import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import com.example.vscapp01.utils.SecurityUtil;

@RestController
@RequestMapping(value = "/member")
public class MemberController {
	

	@Autowired
    MemberService memberService;
	
	@Autowired
	MemberRepository memberRepository; 
	
	@PersistenceContext
	private EntityManager entityManager;
	/*
	 * @RequestMapping("/") public @ResponseBody NewsVo show() { NewsVO vo = new
	 * NewsVO(); vo.setTitle("뉴욕증시 안도 랠리···S&P500 이틀째 최고치 마감");
	 * vo.setContent("나스닥 4주연속 상승세·S&P도 3주째 올라\r\n" + "존슨앤드존슨 백신 폐기로 1%이상 떨어져\r\n" +
	 * "맥도날드는 고객정보 유출에도 1%대 상승"); vo.setCategory("STOCK");
	 * 
	 * return vo; }e=Not Found, status=404).
	 */
	
	//https://needneo.tistory.com/128
	/*
	 * @RequestMapping("/") public @ResponseBody Map<String, Object> show2() {
	 * Map<String, Object> map = new HashMap<> ();
	 * 
	 * map.put("title", "뉴욕증시 안도 랠리···S&P500 이틀째 최고치 마감"); map.put("content",
	 * "나스닥 4주연속 상승세·S&P도 3주째 올라\r\n" + "존슨앤드존슨 백신 폐기로 1%이상 떨어져\r\n" +
	 * "맥도날드는 고객정보 유출에도 1%대 상승");
	 * 
	 * map.put("category", "STOCK");
	 * 
	 * return map; }
	 */
	
	//https://offbyone.tistory.com/391
	//https://shanepark.tistory.com/162
	//https://velog.io/@neity16/3-JPA-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-2-JPA-%EC%8B%9C%EC%9E%91%ED%95%98%EA%B8%B0-%ED%99%98%EA%B2%BD%EC%84%A4%EC%A0%95-Maven-%EA%B0%84%EB%8B%A8%ED%95%9C-%EC%82%AC%EC%9A%A9
	//https://ojt90902.tistory.com/625
	//https://otrodevym.tistory.com/entry/Spring-maven-project-JPA-%ED%95%99%EC%8A%B5
	
	
	@RequestMapping("/1") 
    public String index() {
		System.out.println("1111111111111111");
		
        return "aaaa!!!"; 
    }     
	
	@RequestMapping("/2")
    public String helloWorld() { 
        return "helloWorld"; 
    } 
	
	// 모든 회원 조회
	//http://localhost:8080/member/allmember1
    @GetMapping(value = "/allmember1",produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<MemberEntity>> getAllmembers() throws Exception {
        List<MemberEntity> member = memberService.findAll();
        //return new ResponseEntity<List<MemberVo>>(member, HttpStatus.OK);
        return ResponseEntity.ok(member); //클라이언트가 예상하는 HttpStatus를 설정
    }
    
    // 모든 회원 조회
    @GetMapping(value = "/allmember2",produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<MemberEntity> getAllmembers2() throws Exception {
        List<MemberEntity> member = memberService.findAll();
        
        //return new ResponseEntity<List<MemberVo>>(member, HttpStatus.OK);
        return member; //클라이언트가 예상하는 HttpStatus를 설정
    }
	
    //조회
    @GetMapping(value = "/allmember3",produces = { MediaType.APPLICATION_JSON_VALUE })
    public String getAllmembers3() throws Exception {
        List<MemberEntity> member = memberService.findAll();
        
        //NewsVo news = new NewsVo();
        
        //news
        //member.get(0).
        //return new ResponseEntity<List<MemberVo>>(member, HttpStatus.OK);
        return member.toString(); //클라이언트가 예상하는 HttpStatus를 설정
    }
    
    //조회
    @GetMapping(value = "/allmember4") //,produces = { MediaType.APPLICATION_JSON_VALUE }
    public String getAllmembers4(@RequestParam(value = "name") String name) {
        List membera = entityManager.createQuery("select name from fa_memberinfo where name = :name")
        		.setParameter("name", name)
        		.getResultList();
        //List ls = entityManager.createQuery("select * from ft_memberinfo where name = :name").setParameter("name", name).getResultList();
        //member.se
        return membera.toString(); //클라이언트가 예상하는 HttpStatus를 설정
    }
    
    //조회
    //http://localhost:8080/member/allmember5?name=고양이
    @GetMapping(value = "/allmember5",produces = { MediaType.APPLICATION_JSON_VALUE })
    public String getAllmembers5(@RequestParam(value = "name") String name) {
    	
    	//https://wakestand.tistory.com/668
        return memberRepository.searchParamRepo(name).toString();
    }
    
    //인서트 save
    //http://localhost:8080/member/insert1?id=a&name=aa
    @GetMapping(value = "/insert1",produces = { MediaType.APPLICATION_JSON_VALUE })
    public String insert1(@RequestParam(value = "id") String id, 
    		@RequestParam(value = "name") String name) {
    	
    	System.out.println(id+":"+name);
    	
    	//https://congsong.tistory.com/51
    	MemberEntity memberVo = MemberEntity.builder()
    			.memberId(id)
    			.name(name)
    			.build();
    	memberRepository.save(memberVo);
        return "save ok";
    }
    
    //업데이트
    //http://localhost:8080/member/update1?mbrno=1&name=333333333
    @GetMapping(value = "/update1",produces = { MediaType.APPLICATION_JSON_VALUE })
    public String update1(@RequestParam(value = "mbrno") Long no, 
    		@RequestParam(value = "name") String name) {
    	
    	System.out.println(no+":"+name);
    	
    	Optional<MemberEntity> updateMember= memberRepository.findById(no);
    	
    	//https://math-coding.tistory.com/185
    	//https://makecodework.tistory.com/entry/Java-%EB%9E%8C%EB%8B%A4%EC%8B%9DLambda-%EC%9D%B5%ED%9E%88%EA%B8%B0
    	//https://wonit.tistory.com/130
    	updateMember.ifPresent(selectUser -> {
    		//selectUser.setId("");
    		selectUser.setName(name);
        	memberRepository.save(selectUser);
    	});
    	
    	//mbrno는 (겟또는 포스트로) 대상객체업데이트정보는 포스트로 하는게 맞음
        return "update ok";
    }
    
    
    
    //삭제
    //http://localhost:8080/member/delete1?mbrno=5
    @GetMapping(value = "/delete1",produces = { MediaType.APPLICATION_JSON_VALUE })
    public String delete1(@RequestParam(value = "mbrno") Long no) {
    	
    	System.out.println(no);
    	Optional<MemberEntity> deleteMember= memberRepository.findById(no);
    	deleteMember.ifPresent(deleteUser -> {
        	memberRepository.delete(deleteUser);
    	});
    	
        return "delete ok";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	/*
	 * @RequestMapping("/") public String index() { return
	 * "Greetings from Spring Boot!"; }
	 */
		
	

	//  @GetMapping("/byId")
	//  public ResponseEntity<MemberDto> findMemberInfoById() {
	// 	 return ResponseEntity.ok(memberService.findMemberInfoById(SecurityUtil.getCurrentMemberId()));
	//  }
 
	//  @GetMapping("/{email}")
	//  public ResponseEntity<MemberResponseDto> findMemberInfoByEmail(@PathVariable String email) {
	// 	 return ResponseEntity.ok(memberService.findMemberInfoByEmail(email));
	//  }



	

}
