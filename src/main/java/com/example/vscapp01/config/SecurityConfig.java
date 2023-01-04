package com.example.vscapp01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.vscapp01.Components.JwtTokenProvider;
import com.example.vscapp01.service.MemberServiceImpl;
//https://nahwasa.com/entry/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-Spring-Security-%EA%B8%B0%EB%B3%B8-%EC%84%B8%ED%8C%85-%EC%8A%A4%ED%94%84%EB%A7%81-%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0
//https://zeroco.tistory.com/101

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
//@ComponentScan(basePackages = {"com.example.vscapp01.components"})
public class SecurityConfig{

    private final JwtTokenProvider jwtTokenProvider;
    
    // @Autowired
    // private MemberService memberService;
    
    

    @Bean
    public UserDetailsService userDetailsService() {
        return new MemberServiceImpl(jwtTokenProvider);
    }

    //passwordEncoder
    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // @Bean
	// public PasswordEncoder passwordEncoder() {
    //     //Service에서 비밀번호를 암호화할 수 있도록 Bean으로 등록합니다.
    //     //https://victorydntmd.tistory.com/328
    //    PasswordEncoder encoder = new BCryptPasswordEncoder();
	// 	return encoder;
	// }
    
    // @Bean
    // public WebSecurityCustomizer webSecurityCustomizer() {
    //     return web -> {
    //         web.ignoring()
    //             .antMatchers(
    //                 "/static/css/**",
    //                 "/static/js/**",
    //                 "*.ico"
    //                 );
    //     };
    // }
    
    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //     http.csrf().disable();

        
    //     return http.build();
    // }

    // @Bean
    // public CorsConfigurationSource corsConfigurationSource() {
    //     CorsConfiguration configuration = new CorsConfiguration();
    //     configuration.setAllowCredentials(false); // 쿠키를 받을건지
    //     configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
    //     configuration.setAllowedMethods(Arrays.asList("GET", "POST"));

    //     configuration.addAllowedHeader("*");

    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     source.registerCorsConfiguration("/**", configuration);
    //     return source;

    // }
    
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
        //해당 요청은 인증 대상에서 제외시킵니다.
		return (web) -> web.ignoring().antMatchers("/logIn")
        .antMatchers("/signUp")
        .antMatchers("/css/**")
        .antMatchers("/js/**")
        .antMatchers("/resources/**")
        .antMatchers("/static/**")
        .antMatchers("/bootstrap/**")
        .antMatchers("*.ico");// Resources 파일이나 Javascript 파일 경로 무시 
	} 

    
    //https://bamdule.tistory.com/53
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //http
        //    .authorizeHttpRequests((authz) -> authz
        //        .anyRequest().authenticated()
        //    )
        //    .httpBasic(withDefaults());

        //https://bamdule.tistory.com/53
        //http 요청에 대해서 모든 사용자가 /** 경로로 요청할 수 있지만, /member/** , /admin/** 경로는 인증된 사용자만 요청이 가능합니다. 


        http.cors()
            .and();

        http.authorizeRequests()
            
            .antMatchers("/").permitAll()
            .antMatchers("/logIn", "/signUp", "/css/**", "/js/**", "/resources/**","/static/**", "/bootstrap/**").permitAll()
            .antMatchers("/api/**").permitAll()
            .antMatchers("/user/**").hasRole("USER")
            //.antMatchers("/member/**").hasAnyRole("USER", "ADMIN")
			//.antMatchers("/admin/**").hasRole("ADMIN")
            //.antMatchers("/member/**").authenticated()
            //.antMatchers("/admin/**").authenticated()
            //.antMatchers("/**").permitAll();
            .anyRequest().authenticated();

            

        http.formLogin()
	 		.loginPage("/logIn")
	 		.defaultSuccessUrl("/home")
            //.defaultSuccessUrl("/")
            //.failureUrl("/user/signIn?fail=true");
	 		.usernameParameter("username")
	 		.passwordParameter("password")
            .loginProcessingUrl("/login_proc")
            .failureForwardUrl("/asset_denied")
            .permitAll();

        http.csrf().disable();     
        //http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        //http.httpBasic().disable(); // 일반적인 루트가 아닌 다른 방식으로 요청시 거절, header에 id, pw가 아닌 token(jwt)을 달고 간다. 그래서 basic이 아닌 bearer를 사용한다.

        

	 	http.logout()
	 		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	 		.logoutSuccessUrl("/logIn")
	 		.invalidateHttpSession(true)
            .permitAll();
        
        //권한이 없는 사용자가 접근했을 경우 이동할 경로를 지정합니다.
        http.exceptionHandling()
            .accessDeniedPage("/denied");
            
        //JWT 인증을 위하여 직접 구현한 필터를 UsernamePasswordAuthenticationFilter 전에 실행하겠다는 설정이다.
        // JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        
        //JWT를 사용하기 때문에 세션을 사용하지 않는다는 설정이다. https://gksdudrb922.tistory.com/217
        // + 토큰에 저장된 유저정보를 활용하여야 하기 때문에 CustomUserDetailService 클래스를 생성합니다.
        //세션을 사용하지 않는다고 설정한다.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }


    
    //@Override
    //public void configure(AuthenticationManagerBuilder auth) throws Exception {
    //    auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    //}
    
	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
		
	// 	http.csrf().disable();		// 개발 시 에만
		
	// 	http.authorizeRequests()
	// 		//.antMatchers("/user/**").access("ROLE_USER")				// 사용자 페이지
	// 		//.antMatchers("/admin/**").access("ROLE_ADMIN")				// 관리자 페이지
	// 		//.antMatchers("/login").permitAll()
	// 		//.antMatchers("/**").authenticated();
    //         .anyRequest().authenticated();

	// 	http.formLogin()
	// 		.loginPage("/login")
	// 		.defaultSuccessUrl("/home")
	// 		.usernameParameter("id")
	// 		.passwordParameter("password")
    //         .permitAll();

	// 	http.logout()
	// 		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	// 		.logoutSuccessUrl("/")
	// 		.invalidateHttpSession(true);
		
	// 	//http.authenticationProvider(authProvider);
		
	// }
}