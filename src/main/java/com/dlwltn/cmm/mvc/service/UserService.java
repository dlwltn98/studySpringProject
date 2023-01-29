package com.dlwltn.cmm.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlwltn.cmm.mvc.domain.CmmUser;
import com.dlwltn.cmm.mvc.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    /* myibatis Sql ID */
    private final String MYIBATIS_ID = "com.dlwltn.cmm.mvc.repository.UserRepository.";
    
    
    public List<CmmUser> getUserList() throws Exception {
        return userRepository.getUserList();
    }
    
    public CmmUser getUser(String userId) {
        return userRepository.getUser(userId);
    }
    
    public int saveUser(CmmUser cmmUser) {
        
        int dataCnt = userRepository.updtUser(cmmUser);
        if(dataCnt == 0) {
            dataCnt = userRepository.saveUser(cmmUser);
        }
        return dataCnt;
    }
    
    public int deleteUser(String userId) {
        return userRepository.deleteUser(userId);
    }
}
