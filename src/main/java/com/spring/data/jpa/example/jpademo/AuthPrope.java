package com.spring.data.jpa.example.jpademo;

import org.springframework.beans.factory.annotation.Value;

public class AuthPrope implements Auth {

    @Value("$(duwei.user.name)")
    private String userName;

    @Override
    public String getUserName() {
        return userName;
    }
}
