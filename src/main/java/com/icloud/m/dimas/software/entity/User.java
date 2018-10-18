package com.icloud.m.dimas.software.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class User {

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String salt) {
        this.username = username;
        this.password = password;
        this.salt = salt;
    }

    private String username;
    private String password;
    private String salt;
    private List<String> authorities = new ArrayList<>();
}
