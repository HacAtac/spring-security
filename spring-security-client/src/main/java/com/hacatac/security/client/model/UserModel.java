package com.hacatac.security.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String matchingPassword;

//    public boolean isMatchingPassword() {
//        return this.password.equals(this.matchingPassword);
//    }

}
