package com.wqlm.boot.user.dto;

import lombok.Data;

@Data
public class LoginDTO {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;
    public String getUserName(){return userName;}


    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }
}
