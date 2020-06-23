package com.wqlm.boot.user.service;

import com.wqlm.boot.user.config.ApplicationProperty;
import com.wqlm.boot.user.dao.ModeratorMapper;
import com.wqlm.boot.user.dao.UserMapper;
import com.wqlm.boot.user.dto.LoginDTO;
import com.wqlm.boot.user.dto.RegisterDTO;
import com.wqlm.boot.user.enums.ApplicationEnum;
import com.wqlm.boot.user.exception.ApplicationException;
import com.wqlm.boot.user.po.Moderator;
import com.wqlm.boot.user.po.User;
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
public class ModeratorService {
    @Autowired
    private ModeratorMapper moderatorMapper;
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
        Moderator moderator = new Moderator();
        moderator.setAccount(dto.getUserName());
        moderator.setPassword(dto.getPassword());
        return 1 == moderatorMapper.insert(moderator);
    }

    /**
     * 根据用户ID获取用户
     *
     * @param id
     * @return
     */
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
    private Moderator getUserByName(String name) {
        Moderator moderator = new Moderator();
        moderator.setAccount(name);
        final Moderator moderator1 = moderatorMapper.selectByPrimaryKey(moderator);
        return moderator1;
    }

    /**
     * 登陆
     *
     * @param dto
     */
    public LoginDTO login(LoginDTO dto) {
        Moderator moderator = getUserByName(dto.getUserName());
        if (moderator == null){
            //用户不存在
            throw new ApplicationException(ApplicationEnum.USER_NO_EXIST);
        }
        String loginPassword = dto.getPassword();
        if (!moderator.getPassword().equals(loginPassword)) {
            // 密码不一致
            throw new ApplicationException(ApplicationEnum.PASSWORD_ERR);
        }
        //密码一致登陆成功
        LoginDTO dto1 = new LoginDTO();
        dto1.setUserName(moderator.getAccount());
        return dto1;
    }
}
