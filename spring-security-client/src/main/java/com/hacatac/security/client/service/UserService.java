package com.hacatac.security.client.service;

import com.hacatac.security.client.entity.User;
import com.hacatac.security.client.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);
}
