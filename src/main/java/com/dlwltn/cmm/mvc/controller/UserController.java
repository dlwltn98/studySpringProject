package com.dlwltn.cmm.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dlwltn.cmm.mvc.domain.CmmUser;
import com.dlwltn.cmm.mvc.service.UserService;

/**
 * 
 * @Project : dlwltn98
 * @FileName : UserController.java
 * @Data : 2023. 1. 30.
 * @Author : Leejs
 * @프로그램 설명 : 사용자 관련 컨트롤러
 * @변경 이력 : 
 *
 */

@RequestMapping("/user")
@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/login")
    public String login() {
        return "user/login";
    }
    
    @GetMapping("/signup")
    public ModelAndView signup( Model model) {
        model.addAttribute("userCreateForm", new CmmUser());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/register");
        return modelAndView;
    }
    
    @PostMapping("/signup")
    public String signup ( @ModelAttribute("userCreateForm") CmmUser searchVO
                         , ModelMap model
                         , BindingResult bindingResult ) {
        
        // 오류처리
        if (bindingResult.hasErrors()) {
            return "user/register";
        }
        
        /* pwd 일치 확인 */
        if (!searchVO.getPwd().equals(searchVO.getChkPwd())) {
            bindingResult.rejectValue("chkPwd", "passwordInCorrect", 
                    "2개의 패스워드가 일치하지 않습니다.");
            return "user/register";
        }
        
        
        try {
            // 데이터 저장 또는 수정
            userService.saveUser(searchVO);
            
        } catch(DataIntegrityViolationException e) {
            
            // 데이터 중복 오류일 경우 
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            
            return "user/register";
            
        } catch(Exception e) {
            
            // 그외 다른 오류 발생할 경우
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            
            return "user/register";
        }
        
        
        return "redirect:login";
        
    }
}
