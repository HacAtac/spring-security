package com.hacatac.security.client.controller;

import com.hacatac.security.client.entity.User;
import com.hacatac.security.client.entity.VerificationToken;
import com.hacatac.security.client.event.RegistrationCompleteEvent;
import com.hacatac.security.client.model.UserModel;
import com.hacatac.security.client.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@RestController
@Slf4j
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    @Transactional
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request) {
        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(
                user,
                applicationUrl(request)
        ));
        return "Success";
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        String result = userService.validateVerificationToken(token);
        if(result.equalsIgnoreCase("valid")) {
            return "User Verified Successfully";
        }
        return "Bad User";
    }

    @GetMapping("/resendVerifyToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken,
                                          HttpServletRequest request) {
        VerificationToken verificationToken =
                userService.generateNewVerificationToken(oldToken);

        User user = verificationToken.getUser();
        resendVerificationTokenMail(user, applicationUrl(request), verificationToken);
        return "Verification Link Resent";

    }

    private void resendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken) {

        String url =
                applicationUrl
                        + "/verifyRegistration?token="
                        + verificationToken.getToken();
        //sendVerificationEmail <---- will need to create method to send the email for now we're just mockign it with log
        log.info("Click the link to verify your account: {}",
                url);
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
