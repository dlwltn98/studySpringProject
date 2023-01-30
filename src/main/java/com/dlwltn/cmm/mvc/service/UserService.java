package com.dlwltn.cmm.mvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dlwltn.cmm.mvc.domain.CmmUser;
import com.dlwltn.cmm.mvc.repository.UserRepository;

/**
 * 
 * @Project : dlwltn98
 * @FileName : UserService.java
 * @Data : 2023. 1. 30.
 * @Author : Leejs
 * @프로그램 설명 : 사용자 정보 관련 service
 * @변경 이력 : 
 *
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    public List<CmmUser> getUserList() throws Exception {
        return userRepository.getUserList();
    }
    
    public Optional<CmmUser> getUser(String userId) {
        return userRepository.getUser(userId);
    }
    
    public int saveUser(CmmUser cmmUser) {
        
        // 비밀번호 암호화
        cmmUser.setPwd(passwordEncoder.encode(cmmUser.getPwd()));
        
        //int dataCnt = userRepository.updtUser(cmmUser);
       // if(dataCnt == 0) {
            int dataCnt = userRepository.saveUser(cmmUser);
        //}
        return dataCnt;
    }
    
    public int deleteUser(String userId) {
        return userRepository.deleteUser(userId); 
    }
}
