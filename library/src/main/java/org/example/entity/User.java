package org.example.entity;

import lombok.Data;


@Data
public class User {
    private Integer id;
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(Integer id, String password) {
        this.id = id;
        this.password = password;
    }

    public User() {
        // 默认构造函数
    }
}

