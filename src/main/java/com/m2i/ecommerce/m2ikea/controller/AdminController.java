package com.m2i.ecommerce.m2ikea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping(value="/admin")
    public String admin(){
        return "pagesAdmin/m2ikeadmin";
    }
}
