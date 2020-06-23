package com.wqlm.boot.user.dto;

import lombok.Data;

@Data
public class InviteDTO {
    private String moderatorAccount;
    private String watcherAccount;
    private String groupName;

    public String getModeratorAccount(){
        return moderatorAccount;
    }

    public String getGroupName(){
        return groupName;
    }

    public String getWatcherAccount(){
        return  watcherAccount;
    }
}
