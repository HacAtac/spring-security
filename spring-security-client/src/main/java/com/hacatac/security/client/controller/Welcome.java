package com.hacatac.security.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class Welcome {


    @GetMapping("/welcome")
//    public String hello(Principal principal) {
//        return "Hello " +principal.getName()+", Welcome to Daily Code Buffer!!";
//    }
    public String hello(){
        return "Hello welcome";
    }
}
