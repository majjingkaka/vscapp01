package com.example.vscapp01.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class SpringUser extends User {

    private MemberDto memberDto;

    public SpringUser(String username, String password, Collection<? extends GrantedAuthority> authorities, MemberDto memberDto) {
        super(username, password, authorities);
        this.memberDto = memberDto;
    }

    public MemberDto getMember() {
        return memberDto;
    }

    public void setMember(MemberDto memberDto) {
        this.memberDto = memberDto;
    }
}
