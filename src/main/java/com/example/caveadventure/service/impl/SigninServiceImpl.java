package com.example.caveadventure.service.impl;

import com.example.caveadventure.dao.UserMapper;
import com.example.caveadventure.entity.UserEntity;
import com.example.caveadventure.service.SigninService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录逻辑业务
 */
@Service("SigninService")
public class SigninServiceImpl implements SigninService {
    @Autowired
    private UserMapper usermapper;  /* 用户成员变量 */

    /* 登录功能实现 */
    public Boolean signin(String username, String password) {
        UserEntity user = usermapper.findByUsername(username);

        if (user != null && password.equals(user.getPwd())) {  //检查密码上是否正确
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;    //用户不存在或密码错误
        }
    }
}
