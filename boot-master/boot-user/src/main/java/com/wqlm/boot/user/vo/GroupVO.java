package com.wqlm.boot.user.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GroupVO {
    private String groupName;
    private String moderatorAccount;
    private List<String> watcherList=new ArrayList<>();

    public void setGroupName(String groupName){
        this.groupName = groupName;
    }
    public void setModeratorAccount(String moderatorAccount){
        this.moderatorAccount = moderatorAccount;
    }
    public void setWatcherList(List<String> watcherList){
        this.watcherList = watcherList;
    }

}
