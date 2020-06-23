package com.wqlm.boot.user.dto;

import lombok.Data;

@Data
public class GetGroupDTO {
    private String groupName;

    public String getGroupName(){
        return  groupName;
    }
}
