package com.example.vscapp01.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

//import com.example.vscapp01.config.JwtTokenInfo;
import com.example.vscapp01.dto.MemberDto;
import com.example.vscapp01.entity.MemberEntity;

public interface MemberService extends UserDetailsService{
	
    public List<MemberEntity> findAll() throws Exception;
    public List<MemberDto> getFindAll() throws Exception;
    public ResponseEntity<MemberEntity> getAllUsers() throws Exception;
    public ResponseEntity<Integer> createMember(MemberDto memberDto) throws Exception;

    public MemberDto findMemberInfoById(Long memberId);
    public MemberDto findMemberInfoByEmail(String email);

    // public JwtTokenInfo login(String memberId, String password);
    
    // @Override
    // public UserDetails loadUserByUsername(String username);

    public String createToken(MemberEntity MemberEntity);
}
