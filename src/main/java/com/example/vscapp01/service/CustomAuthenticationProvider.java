package com.example.vscapp01.service;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.vscapp01.dto.SpringUser;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider{
    //private static Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
    private Logger logger = LogManager.getLogger(CustomAuthenticationProvider.class);
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // 검쯩을 위한 구현
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        logger.debug("CustomAuthenticationProvider authenticate method call..");

        //String username = authentication.getName();
        String username = (String)authentication.getPrincipal();
        String password = (String)authentication.getCredentials();

        //AccountContext accountContext = (AccountContext) userDetailsService.loadUserByUsername(username);
        SpringUser springUser = (SpringUser)userDetailsService.loadUserByUsername(username);
        logger.debug("username : " + springUser.getUsername() + " / password : " + springUser.getPassword());

        logger.log(Level.ALL,"LOG TEST");
        logger.trace("TRACE TEST");
        logger.info("INFO TEST");
        logger.debug("DEBUG TEST");
        logger.warn("WARN TEST");
        logger.error("ERROR TEST");
        logger.fatal("FATAL TEST");
        System.out.println(logger.getLevel());
        System.out.println(logger.getLevel().intLevel());

        // password 일치하지 않으면!
        if(!passwordEncoder.matches(password,springUser.getPassword())){
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(username,password,springUser.getAuthorities());

        return authenticationToken;
    }

    // 토큰 타입과 일치할 때 인증
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
