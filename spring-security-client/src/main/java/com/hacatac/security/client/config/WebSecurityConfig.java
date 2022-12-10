package com.hacatac.security.client.config;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig {

    @Bean //we define this as a bean because it's not automatically created by spring as a bean(not a component but a configuration)
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }

}
