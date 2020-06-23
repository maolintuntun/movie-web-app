package com.wqlm.boot.user.dto;

import lombok.Data;

@Data
public class GetGroupsDTO {
    private String type;
    private String account;

    public String getType(){
        return type;
    }

    public String getAccount(){
        return account;
    }
}
