package com.wqlm.boot.user.dto;

import lombok.Data;

@Data
public class JoinGroupDTO {


    private String watcherAccount;
    private String groupName;

    public String getWatcherAccount(){
        return watcherAccount;
    }

    public String getGroupName(){
        return groupName;
    }
}
