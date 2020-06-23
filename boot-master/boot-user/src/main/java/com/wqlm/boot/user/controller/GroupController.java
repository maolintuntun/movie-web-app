package com.wqlm.boot.user.controller;

import com.wqlm.boot.user.dto.*;
import com.wqlm.boot.user.service.GroupService;
import com.wqlm.boot.user.service.MailService;
import com.wqlm.boot.user.vo.GroupVO;
import com.wqlm.boot.user.vo.GroupsVO;
import com.wqlm.boot.user.vo.result.FailResult;
import com.wqlm.boot.user.vo.result.Result;
import com.wqlm.boot.user.vo.result.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/group")
@Validated
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private MailService mailService;

    @PostMapping("/create")
    public Result createGroup(@Valid CreateGroupDTO dto) {
        System.out.println("groupname:" + dto.getGroupName());
        System.out.println("mname:" + dto.getModeratorAccount());
        boolean result = groupService.createGroup(dto);
        if (result) {
            return new SuccessResult<>();
        }
        return new FailResult();
    }

    @PostMapping("/join")
    public Result joinGroup(@Valid JoinGroupDTO dto) {
        boolean result = groupService.joinGroup(dto);
        if (result) {
            return new SuccessResult<>();
        }
        return new FailResult();
    }

    @PostMapping("/invite")
    public Result invite(@Valid InviteDTO dto) {
        boolean result = mailService.sendSimpleMail(dto);
        if (result) {
            return new SuccessResult<>();
        }
        return new FailResult();
    }

    @PostMapping ("/unsubscribe")
    public Result unsubscribe(@Valid UnsubcribeDTO dto) {
        boolean result = groupService.unsubcribe(dto);
        if (result) {
            return new SuccessResult<>();
        }
        return new FailResult();
    }

    @GetMapping ("/getGroup")
    public Result getGroup (@Valid GetGroupDTO dto) {
        GroupVO vo = groupService.getGroup(dto);
        return new SuccessResult<>(vo);
    }

    @GetMapping ("/getGroups")
    public Result getGroups (@Valid GetGroupsDTO dto) {
        if(dto.getType().equals("Moderator")){
            GroupsVO voM = groupService.getGroupsM(dto);
            return new SuccessResult<>(voM);
        }else{
            GroupsVO voW = groupService.getGroupsW(dto);
            return new SuccessResult<>(voW);
        }
    }


}
