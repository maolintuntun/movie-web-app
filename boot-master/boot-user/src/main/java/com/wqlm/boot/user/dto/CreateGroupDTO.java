package com.wqlm.boot.user.dto;

import lombok.Data;

@Data
public class CreateGroupDTO {

    private String moderatorAccount;

    private String groupName;

    public String getModeratorAccount(){
        return moderatorAccount;
    }

    public String getGroupName(){
        return groupName;
    }

}
