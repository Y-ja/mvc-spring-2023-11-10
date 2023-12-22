package com.spring.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

@EnableWebFluxSecurity
public class SecurityCofig {
    //시큐리티 기본설정
    @Bean
    public DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**")
                .permitAll();
        return  http.build();
    }

//    @Bean
//    public PasswordEncoder
}
