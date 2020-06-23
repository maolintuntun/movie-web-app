package com.wqlm.boot.user.service;

import com.wqlm.boot.user.dao.GroupHasWatcherMapper;
import com.wqlm.boot.user.dao.GroupMapper;
import com.wqlm.boot.user.dto.*;
import com.wqlm.boot.user.po.Group;
import com.wqlm.boot.user.po.GroupHasWatcher;
import com.wqlm.boot.user.po.User;
import com.wqlm.boot.user.vo.GroupVO;
import com.wqlm.boot.user.vo.GroupsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wqlm.boot.user.enums.ApplicationEnum;
import com.wqlm.boot.user.exception.ApplicationException;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private GroupHasWatcherMapper groupHasWatcherMapper;

    public boolean createGroup(CreateGroupDTO cdto) {

        //if (getUserByName(cdto.getGroupName()) != null) {
        //    //组名已存在
        //    throw new ApplicationException(ApplicationEnum.USER_NAME_REPETITION);
        //}

        Group group = new Group();
        group.setModeratorAccount(cdto.getModeratorAccount());
        group.setName(cdto.getGroupName());

        return 1 == groupMapper.insert(group);
    }

    private Group getUserByName(String name) {
        Group group = new Group();
        group.setName(name);
        return groupMapper.selectOne(group);
    }

    public boolean joinGroup(JoinGroupDTO dto) {

        //if (getUserByName(cdto.getGroupName()) != null) {
            //组名已存在
        //    throw new ApplicationException(ApplicationEnum.USER_NAME_REPETITION);
        //}
        GroupHasWatcher groupHasWatcher = new GroupHasWatcher();
        groupHasWatcher.setGroupName(dto.getGroupName());
        groupHasWatcher.setWatcherAccount(dto.getWatcherAccount());

        return 1 == groupHasWatcherMapper.insert(groupHasWatcher);
    }

    public boolean unsubcribe(UnsubcribeDTO dto) {

        //if (getUserByName(cdto.getGroupName()) != null) {
        //组名已存在
        //    throw new ApplicationException(ApplicationEnum.USER_NAME_REPETITION);
        //}
        GroupHasWatcher groupHasWatcher = new GroupHasWatcher();
        groupHasWatcher.setGroupName(dto.getGroupName());
        groupHasWatcher.setWatcherAccount(dto.getWatcherAccount());

        return 1 == groupHasWatcherMapper.delete(groupHasWatcher);
    }

    public GroupVO getGroup(GetGroupDTO dto) {
        Group group = groupMapper.selectByPrimaryKey(dto.getGroupName());
        //GroupHasWatcher groupHasWatcher = groupHasWatcherMapper.selectByPrimaryKey(dto.getGroupName());
        GroupVO groupVO = new GroupVO();
        groupVO.setGroupName(group.getName());
        groupVO.setModeratorAccount(group.getModeratorAccount());

        GroupHasWatcher groupHasWatcher = new GroupHasWatcher();
        groupHasWatcher.setGroupName(dto.getGroupName());
        List<GroupHasWatcher> watcherList= groupHasWatcherMapper.select(groupHasWatcher);
        List<String> list=new ArrayList<>();
        for(int i = 0; i< watcherList.size(); i++){
            list.add(watcherList.get(i).getWatcherAccount());
        }
        groupVO.setWatcherList(list);
        return groupVO;
    }

    public GroupsVO getGroupsM(GetGroupsDTO dto){
        Group group = new Group();
        group.setModeratorAccount(dto.getAccount());
        List<Group> groupList = groupMapper.select(group);
        List<GroupVO> list =new ArrayList<>();

        for(int i = 0; i< groupList.size(); i++) {
            GroupVO groupVO = new GroupVO();
            groupVO.setGroupName(groupList.get(i).getName());
            groupVO.setModeratorAccount(groupList.get(i).getModeratorAccount());

            GroupHasWatcher groupHasWatcher = new GroupHasWatcher();
            groupHasWatcher.setGroupName(groupList.get(i).getName());
            List<GroupHasWatcher> watcherList = groupHasWatcherMapper.select(groupHasWatcher);
            List<String> list2 = new ArrayList<>();
            for(int j = 0; j< watcherList.size(); j++){
                list2.add(watcherList.get(j).getWatcherAccount());
            }
            groupVO.setWatcherList(list2);
            list.add(groupVO);
        }
        GroupsVO groupsVO = new GroupsVO();
        groupsVO.setGroupList(list);
        return groupsVO;
    }

    public GroupsVO getGroupsW(GetGroupsDTO dto){

        GroupHasWatcher groupHasWatcher = new GroupHasWatcher();
        groupHasWatcher.setWatcherAccount(dto.getAccount());
        List<GroupHasWatcher> groupHasWatcherList = groupHasWatcherMapper.select(groupHasWatcher);
        List<GroupVO> list =new ArrayList<>();

        for(int i = 0; i< groupHasWatcherList.size(); i++) {
            GroupVO groupVO = new GroupVO();
            groupVO.setGroupName(groupHasWatcherList.get(i).getGroupName());

            GroupHasWatcher groupHasWatcher2 = new GroupHasWatcher();
            groupHasWatcher2.setGroupName(groupHasWatcherList.get(i).getGroupName());
            List<GroupHasWatcher> watcherList = groupHasWatcherMapper.select(groupHasWatcher2);
            List<String> list2 = new ArrayList<>();
            for(int j = 0; j< watcherList.size(); j++){
                list2.add(watcherList.get(j).getWatcherAccount());
            }
            groupVO.setWatcherList(list2);

            Group group = groupMapper.selectByPrimaryKey(groupHasWatcherList.get(i).getGroupName());
            groupVO.setModeratorAccount(group.getModeratorAccount());
            list.add(groupVO);

        }

        GroupsVO groupsVO = new GroupsVO();
        groupsVO.setGroupList(list);
        return groupsVO;
    }

}
