package com.myblog.myblog11.Test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Test {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();//Here instance of BcryptPasswordEncoder is created and stored in the reference variable of interface PasswordEncoder.

        System.out.println(passwordEncoder.encode("rawat"));




    }

}
