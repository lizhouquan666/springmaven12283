package com.wanxi.entity;

import lombok.Data;

@Data
public class User extends Base {
    private String username;
    private String password;
    private String code;
    private String sessionCode;
    private String address;
    private String email;
    private String phone;
    private String sex;
    private String birthday;
    private String hobby;
    private String start;
    private String end;
    private String text;
    private String city;

}
