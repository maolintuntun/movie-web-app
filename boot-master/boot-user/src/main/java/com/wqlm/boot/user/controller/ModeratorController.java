package com.wqlm.boot.user.controller;


import com.wqlm.boot.user.dto.LoginDTO;
import com.wqlm.boot.user.dto.RegisterDTO;
import com.wqlm.boot.user.service.ModeratorService;
import com.wqlm.boot.user.vo.result.FailResult;
import com.wqlm.boot.user.vo.result.Result;
import com.wqlm.boot.user.vo.result.SuccessResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/moderator")
@Validated
public class ModeratorController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ModeratorService moderatorService;

    //@Autowired
    //private RedisOperator redisOperator;

    /**
     * 注册
     * 参数通过表单传递
     *
     * @param dto
     * @return
     */
    @PostMapping("/register")
    public Result register(@Valid RegisterDTO dto) {
        boolean result = moderatorService.register(dto);
        if (result) {
            return new SuccessResult<>();
        }
        return new FailResult();
    }

    /**
     * 登陆接口
     *
     * @return
     */
    @PostMapping("/login")
    public Result login(LoginDTO dto) {
        LoginDTO vo = moderatorService.login(dto);
        if (vo == null) {
            return new FailResult();
        }
        return new SuccessResult<>(vo);
    }

    /**
     * 获取用户信息
     * 参数通过url传递
     *
     * @param id
     * @return
     */
//    @GetMapping
//    public Result getUser(@NotNull(message = "id不能为空") Integer id) {
//        //UserVO vo = moderatorService.get(id);
//        //return new SuccessResult<>(vo);
//    }

}