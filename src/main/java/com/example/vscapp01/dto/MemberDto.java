package com.example.vscapp01.dto;

import java.io.Serializable;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.Length;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class MemberDto implements Serializable{

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String id;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    @Pattern(regexp = "^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$")//https://gmlwjd9405.github.io/2018/12/25/difference-dao-dto-entity.html
    private String email;

    //@JsonIgnore
    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 4, max = 16, message = "비밀번호는 4자 이상, 16자 이하로 입력해주세요.")
    private String password;

    @NotEmpty(message = "주소는 필수 입력 값입니다.")
    private String address;

    //https://wildeveloperetrain.tistory.com/101
    //https://gmlwjd9405.github.io/2018/12/25/difference-dao-dto-entity.html
    @Builder
    public MemberDto(String id, String name, String email, String password, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
    }
}
