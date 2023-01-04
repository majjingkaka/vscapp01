package com.example.vscapp01.entity;

import java.util.ArrayList;
//import java.util.Collection;
import java.util.List;
//import java.util.stream.Collectors;

import javax.persistence.*;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

//import jakarta.persistence.*;
import lombok.*;


//@AllArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor //access 속성을 이용해서 동일한 패키지 내의 클래스에서만 객체를 생성할 수 있도록 제어
@Entity(name = "member")
@Table(name = "fa_memberinfo") // 기본적으로 클래스명(Camel Case)을 테이블명(Snake Case)으로 매핑합니다.
@Data
public class MemberEntity{
	
	
	@Id //pk명시
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment 명시 기본 키 생성을 DB에 위임 (Mysql)
	@Column(name = "member_no")
	private Long memberNo; //적을땐 Integer가능
	
	/*
	 * @GeneratedValue(startegy = GenerationType.IDENTITY) 기본 키 생성을 DB에 위임 (Mysql)
	 * @GeneratedValue(startegy = GenerationType.SEQUENCE) DB시퀀스를 사용해서 기본 키 할당(ORACLE)
	 * @GeneratedValue(startegy = GenerationType.TABLE) 키 생성 테이블 사용 (모든 DB 사용 가능)
	 * @GeneratedValue(startegy = GenerationType.AUTO) 선택된 DB에 따라 자동으로 전략 선택
	 */
	
	
	@Column(name = "member_id", nullable = false)
    private String memberId;
	
	// @ElementCollection(fetch = FetchType.EAGER)
    // @Builder.Default
    // private List<String> roles = new ArrayList<>();
 
    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    //     return this.roles.stream()
    //             .map(SimpleGrantedAuthority::new)
    //             .collect(Collectors.toList());
    // }
	
	@Column(name = "name")
    private String name;

	@Column(name = "email")
    private String email;

	@Column(name = "password", length = 255, nullable = false)
    private String password;


	//@ElementCollection(fetch = FetchType.EAGER)
    //@Builder.Default

	//@ManyToOne //https://dev-coco.tistory.com/106
    //@JoinColumn(name = "role")

	@OneToMany(mappedBy = "memberId", orphanRemoval = true, cascade = CascadeType.ALL)
	//@JoinColumn(name = "member_id")
    private List<RoleEntity> roles = new ArrayList<>();
	

	//@Column(name = "roles")
	//private List<RoleEntity> roles = new ArrayList<>();
	

	//https://www.icatpark.com/entry/JPA-%EA%B8%B0%EB%B3%B8-Annotation-%EC%A0%95%EB%A6%AC
	//https://ddol.tistory.com/39
	//https://cjw-awdsd.tistory.com/46
	
	/*
	 * create : 기존테이블 삭제 후 다시 생성 (DROP + CREATE) create-drop : create와 같으나 종료시점에 테이블
	 * DROP update : 변경분만 반영 (운영 DB에는 사용하면 안됨) validate : 엔티티와 테이블이 정상 매핑되었는지만 확인
	 * none : 기능 자체를 사용하지 않겠다고 표시 delete -> 지우는 것은 지원하지 않음 주의 !!
	 * 
	 * - 운영 장비에는 절대 create, create-drop, update 사용하면 안된다.
	 * 
	 * - 개발 초기 단계 : create or update
	 * 
	 * - 테스트 서버 : update or validate 권장 / create할 경우 테스트 데이터 다 날라감
	 * 
	 * - 스테이징과 운영 서버 : validate or none 권장이지만 가급적 사용 x
	 */
	
	
	//https://brownbears.tistory.com/180
	
	/*
    @Builder
    public MemberVo(String id, String name) {
    	//this.mbrNo = mbrNo;
        this.id = id;
        this.name = name;
    }
	*/
	
    //컬럼에 대한 setter를 무작정 생성하는 경우, 객체의 값이 어느 시점에 변경되었는지 알 수가 없습니다.
    //이러한 이유로 Entity 클래스에는 절대로 Set 메서드가 존재해서는 안된답니다.
    //https://congsong.tistory.com/51
    //https://mangkyu.tistory.com/78
	
	
	
	
	/*
	public Long getMbrNo() {
		return mbrNo;
	}

	public void setMbrNo(Long mbrNo) {
		this.mbrNo = mbrNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	*/
	
	
	//Lombok @Data 어노테이션 “getter” “setter” 인식 안될때
	//https://offetuoso.github.io/blog/develop/troubleshooting/spring/lombok-error/
		
	
}
