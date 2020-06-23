package com.wqlm.boot.user.vo;

import com.wqlm.boot.user.dto.GetGroupDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GroupsVO {
    //private List<String> groupList=new ArrayList<>();
    private List<GroupVO> groupList=new ArrayList<>();

    //public void setGroupList(List<String> groupList){
    //    this.groupList =groupList;
    //}
    public void setGroupList(List<GroupVO> groupList){ this.groupList =groupList; }
}
