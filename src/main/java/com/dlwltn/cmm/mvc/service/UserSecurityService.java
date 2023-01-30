package com.dlwltn.cmm.mvc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dlwltn.cmm.mvc.UserRole;
import com.dlwltn.cmm.mvc.domain.CmmUser;
import com.dlwltn.cmm.mvc.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * 
 * @Project : dlwltn98
 * @FileName : UserSecurityService.java
 * @Data : 2023. 1. 30.
 * @Author : Leejs
 * @프로그램 설명 : 
 * @변경 이력 : 
 *
 */

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        // loadUserByUsername : 사용자명으로 비밀번호를 조회하여 리턴하는 메서드
        
        // 사용자가 없을 경우
        Optional<CmmUser> _siteUser = this.userRepository.getUser(userId);
        if (_siteUser.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }
        
        
        CmmUser siteUser = _siteUser.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        
        // 사용자명이 admin인 경우 admin 권한 부여, 그 외에는 user 권한 부여
        if ("admin".equals(userId)) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
            
        } else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }
        
        // user 객체 생성해 리턴
        // 리턴된 User 객체의 비밀번호가 화면으로부터 입력 받은 비밀번호와 일치하는지를 검사 (로직 내장)
        return new User(siteUser.getUserId(), siteUser.getPwd(), authorities);
    }

}
