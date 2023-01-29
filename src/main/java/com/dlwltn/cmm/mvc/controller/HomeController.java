package com.dlwltn.cmm.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class HomeController {

    // @GetMapping("/home")
    public String home() {
        return "home";
    }
    
    
}
