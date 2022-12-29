package com.example.vscapp01.config;

import java.security.AuthProvider;
import java.util.Arrays;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.vscapp01.service.MemberService;
import com.example.vscapp01.service.MemberServiceImpl;

import lombok.RequiredArgsConstructor;

//https://nahwasa.com/entry/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-Spring-Security-%EA%B8%B0%EB%B3%B8-%EC%84%B8%ED%8C%85-%EC%8A%A4%ED%94%84%EB%A7%81-%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0
//https://zeroco.tistory.com/101


@Configuration
@EnableWebSecurity
public class SecurityConfig{

    // @Autowired
    // private MemberService memberService;
    
    

    @Bean
    public UserDetailsService userDetailsService() {
        return new MemberServiceImpl();
    }

    //passwordEncoder
    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
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
        http.authorizeRequests()
            
            .antMatchers("/").permitAll()
            .antMatchers("/logIn", "/signUp", "/css/**", "/js/**", "/resources/**").permitAll()
            .antMatchers("/api/**").permitAll()
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
            
	 	http.logout()
	 		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	 		.logoutSuccessUrl("/logIn")
	 		.invalidateHttpSession(true)
            .permitAll();
        
            //권한이 없는 사용자가 접근했을 경우 이동할 경로를 지정합니다.
        http.exceptionHandling()
            .accessDeniedPage("/denied");

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