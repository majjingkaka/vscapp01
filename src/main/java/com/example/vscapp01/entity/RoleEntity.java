package com.example.vscapp01.entity;

import java.util.List;

//import org.springframework.data.annotation.Id;
//import org.springframework.security.core.userdetails.User;

//import java.util.ArrayList;
//import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name="role")
@Table(name="fa_roles")
@Data
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_no")
    private Long roleNo;

    //@ManyToOne
    //@JoinColumn(name = "member_id")
    @Column(name = "member_id", nullable=false)
    private String memberId;
    //private List<MemberEntity> memberEntity;

    @Column(name = "role_name", nullable=false)
    private String rolename;

    //@ManyToMany(mappedBy="roles")
    //private List<User> users;

    public RoleEntity(String rolename) {
        this.rolename = rolename;
    }
}
