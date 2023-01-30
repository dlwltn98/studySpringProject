package com.dlwltn.cmm.mvc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.dlwltn.cmm.mvc.domain.CmmUser;

@Repository
public interface UserRepository {
    
    // 로그인을 위한 사용자 정보 조회
    //Optional<CmmUser> findByusername(String userId);

    // 사용자 정보 리스트 조회
    List<CmmUser> getUserList();
    
    // 사용자 정보 조회
    Optional<CmmUser> getUser(String userId);
    
    // 사용자 정보 등록
    int saveUser(CmmUser cmmUser);
    
    // 사용자 정보 수정
    int updtUser(CmmUser cmmUser);
    
    // 사용자 정보 삭제
    int deleteUser(String userId);
}
