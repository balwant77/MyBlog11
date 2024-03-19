package com.myblog.myblog11.Util;

public class Login {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Login(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
