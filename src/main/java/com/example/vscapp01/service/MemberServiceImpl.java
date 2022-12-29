package com.example.vscapp01.service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.example.vscapp01.common.DefaultRes;
//import com.example.vscapp01.common.ResponseMessage;
//import com.example.vscapp01.common.StatusCode;
import com.example.vscapp01.dto.MemberDto;
import com.example.vscapp01.dto.SpringUser;
import com.example.vscapp01.entity.MemberEntity;
import com.example.vscapp01.mapper.MemberMapper;
import com.example.vscapp01.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //https://computer-science-student.tistory.com/622
public class MemberServiceImpl implements MemberService{

    @Autowired
	private MemberRepository memberRepository;

    @Autowired
    private MemberMapper memberMapper;
    






	public List<MemberEntity> findAll() throws Exception{
        List<MemberEntity> members = new ArrayList<>();
        memberRepository.findAll().forEach(e -> members.add(e));
        
        return members;
    }







    public List<MemberDto> getFindAll() throws Exception{
        //List<MemberEntity> members = new ArrayList<>();
        return memberMapper.getFindAll();
    }













	// 멤버 전체 조회
    public ResponseEntity<MemberEntity> getAllUsers() throws Exception{
        MemberEntity memberEntity = new MemberEntity();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        //final List<MemberEntity> userList = memberMapper.findAll();
        //if (userList.isEmpty())
            //return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER);
        //return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_USER, userList);
        //https://thalals.tistory.com/268
        
        return new ResponseEntity<>(memberEntity, header, HttpStatus.OK);
    }





















    private static final String ROLE_PREFIX = "ROLE_";

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        MemberDto memberDto = memberMapper.selectMember(memberId);
        
        //if(memberDto == null) {
        //    throw new UsernameNotFoundException("사용자 정보가 존재하지 않습니다.");
        //}

        //List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        //roles.add(new SimpleGrantedAuthority("ROLE_MEMBER"));

        //List<Role> userRoleList = memberRepository.selectMemberRole(member);

        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        //for (Role role: userRoleList) {
        //    roles.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName()));
        //}
        roles.add(new SimpleGrantedAuthority(ROLE_PREFIX + "MEMBER"));

        SpringUser springUser = new SpringUser(memberDto.getId(), memberDto.getPassword(), roles, memberDto);

        return springUser;
    }
	
	
	/*
    public Optional<MemberVo> findById(Long mbrNo) {
        Optional<MemberVo> member = memberRepository.findById(mbrNo);
        return member;
    }

    public void deleteById(Long mbrNo) {
        memberRepository.deleteById(mbrNo);
    }

    public MemberVo save(MemberVo member) {
        memberRepository.save(member);
        return member;
    }

    public void updateById(Long mbrNo, MemberVo member) {
        Optional<MemberVo> e = memberRepository.findById(mbrNo);

        if (e.isPresent()) {
            e.get().setMbrNo("1"); //member.getMbrNo()
            e.get().setId("2"); //member.getId()
            e.get().setName("3"); //member.getName()
            memberRepository.save(member);
        }
    }
    */
	
	//https://goddaehee.tistory.com/209

    @Transactional
    //@Override
    public ResponseEntity<Integer> createMember(MemberDto memberDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        
        //MemberEntity memberEntity = MemberEntity.builder()
        //.id(memberDto.getId())
        //.name(memberDto.getName())
        //.password(passwordEncoder.encode(memberDto.getPassword()))
        //.build();
        
        //member.setLastAccessDt(LocalDateTime.now());
        //member.setRegDt(LocalDateTime.now());

        // 비밀번호 암호화
        //BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        //member.setPassword(passwordEncoder2.encode(memberEntity.getPassword()));
        
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        //memberRepository.save(memberEntity);
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        //memberMapper.createMember(memberDto);
        return new ResponseEntity<>(memberMapper.createMember(memberDto), header, HttpStatus.OK);
    }
    
}
