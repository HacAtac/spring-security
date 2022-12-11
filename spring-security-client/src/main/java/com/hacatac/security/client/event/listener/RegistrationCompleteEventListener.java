package com.hacatac.security.client.event.listener;

import com.hacatac.security.client.entity.User;
import com.hacatac.security.client.event.RegistrationCompleteEvent;
import com.hacatac.security.client.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import java.util.UUID;

@Slf4j
public class RegistrationCompleteEventListener implements
        ApplicationListener<RegistrationCompleteEvent> {
    @Autowired
    private UserService userService;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //Create the Verification Token for the User withi Link
         User user = event.getUser();
         String token = UUID.randomUUID().toString();
         userService.saveVerificationTokenForUser(token, user);
        //When created, we can Send Mail to user
        String url =
                event.getApplicationurl()
                        + "verifyRegistration?token="
                        + token;
        //sendVerificationEmail <---- will need to create method to send the email for now we're just mockign it with log
        log.info("Click the link to verify your account: {}",
                url);
    }
}
