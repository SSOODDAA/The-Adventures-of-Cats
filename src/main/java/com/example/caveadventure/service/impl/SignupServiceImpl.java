package com.example.caveadventure.service.impl;

import com.example.caveadventure.dao.UserMapper;
import com.example.caveadventure.entity.UserEntity;
import com.example.caveadventure.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 注册逻辑业务
 */
@Service("SignupService")
public class SignupServiceImpl implements SignupService {
    @Autowired
    private UserMapper usermapper;  /* 用户成员变量 */

    /* 注册功能实现 */
    public Boolean signup(String username, String password){
        //确定表中不存在相同username
        UserEntity search = usermapper.findByUsername(username);

        try {
            assert search == null;

            UserEntity user = new UserEntity();
            user.setUsername(username);
            user.setPwd(password);
            usermapper.insert(user);
        }catch (Exception e){
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

}
