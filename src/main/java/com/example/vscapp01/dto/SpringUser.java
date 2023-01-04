package com.example.vscapp01.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.vscapp01.entity.MemberEntity;

import java.util.Collection;

public class SpringUser extends User {

    private MemberEntity memberEntity;

    public SpringUser(String username, String password, Collection<? extends GrantedAuthority> authorities, MemberEntity memberEntity) {
        super(username, password, authorities);
        this.memberEntity = memberEntity;
    }

    public MemberEntity getMemberEntity() {
        return memberEntity;
    }

    public void setMemberEntity(MemberEntity memberEntity) {
        this.memberEntity = memberEntity;
    }
}
