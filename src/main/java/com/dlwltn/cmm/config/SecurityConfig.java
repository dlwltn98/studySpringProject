package com.dlwltn.cmm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * 
 * @Project : dlwltn98
 * @FileName : SecurityConfig.java
 * @Data : 2023. 1. 28.
 * @Author : Leejs
 * @프로그램 설명 : 스프링 시큐리티 설정
 * @변경 이력 : 
 *
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // and().formLogin().loginPage("/user/login").defaultSuccessUrl("/") : 스프링 시큐리티의 로그인 설정을 담당하는 부분
        http.authorizeHttpRequests().requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll()
            .and()
                .csrf().ignoringRequestMatchers(
                        new AntPathRequestMatcher("/h2-console/**"))
            .and()
                .headers()
                .addHeaderWriter(new XFrameOptionsHeaderWriter(
                        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
            .and()
                .formLogin()
                .loginPage("/user/login")
                .defaultSuccessUrl("/")
            ;

        return http.build();

    }
}
