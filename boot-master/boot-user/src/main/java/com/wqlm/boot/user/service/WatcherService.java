package com.wqlm.boot.user.service;

import com.wqlm.boot.user.config.ApplicationProperty;
import com.wqlm.boot.user.dao.WatcherMapper;
import com.wqlm.boot.user.dto.LoginDTO;
import com.wqlm.boot.user.dto.RegisterDTO;
import com.wqlm.boot.user.enums.ApplicationEnum;
import com.wqlm.boot.user.exception.ApplicationException;
import com.wqlm.boot.user.po.Moderator;
import com.wqlm.boot.user.po.User;
import com.wqlm.boot.user.po.Watcher;
import com.wqlm.boot.user.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.stereotype.Service;
//import org.springframework.util.DigestUtils;
//import com.wqlm.boot.user.util.redis.RedisOperator;
//import com.wqlm.boot.user.dto.ModifyPasswordDTO;

import java.util.UUID;

@Service
public class WatcherService {
    @Autowired
    private WatcherMapper watcherMapper;
    //@Autowired
    //private RedisOperator redisOperator;
    @Autowired
    private ApplicationProperty applicationProperty;
    /**
     * 用户注册
     *
     * @param dto
     * @return
     */
    public boolean register(RegisterDTO dto) {
        if (getUserByName(dto.getUserName()) != null) {
            //用户名已存在
            throw new ApplicationException(ApplicationEnum.USER_NAME_REPETITION);
        }
        Watcher watcher = new Watcher();
        watcher.setAccount(dto.getUserName());
        watcher.setPassword(dto.getPassword());
        return 1 == watcherMapper.insert(watcher);
    }

//    /**
//     * 根据用户ID获取用户
//     *
//     * @param id
//     * @return
//     */
//    public UserVO getUser(Integer id) {
//        User user = userMapper.selectByPrimaryKey(id);
//        UserVO vo = new UserVO();
//        BeanUtils.copyProperties(vo, user);
//        return vo;
//    }
    /**
     * 根据用户名查询用户
     *
     * @param name
     * @return
     */
    private Watcher getUserByName(String name) {
        Watcher watcher = new Watcher();
        watcher.setAccount(name);
        final Watcher watcher1= watcherMapper.selectByPrimaryKey(watcher);
        return watcher1;
    }

    /**
     * 登陆
     *
     * @param dto
     */
    public LoginDTO login(LoginDTO dto) {
        Watcher watcher = getUserByName(dto.getUserName());
        if (watcher == null) {
            //用户不存在
            throw new ApplicationException(ApplicationEnum.USER_NO_EXIST);
        }
        String loginPassword = dto.getPassword();
        if (!watcher.getPassword().equals(loginPassword)) {
            // 密码不一致
            throw new ApplicationException(ApplicationEnum.PASSWORD_ERR);
        }
        //密码一致登陆成功
        LoginDTO dto1 = new LoginDTO();
        dto1.setUserName(watcher.getAccount());
        return dto1;
    }
    

}
