package com.hacatac.security.client.controller;

import com.hacatac.security.client.entity.User;
import com.hacatac.security.client.event.RegistrationCompleteEvent;
import com.hacatac.security.client.model.UserModel;
import com.hacatac.security.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request){
            User user = userService.registerUser(userModel);
            publisher.publishEvent(new RegistrationCompleteEvent(
                    user,
                    applicationUrl(request)
            ));
            return "Success";
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
