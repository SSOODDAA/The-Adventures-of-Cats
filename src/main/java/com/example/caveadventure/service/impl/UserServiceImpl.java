package com.example.caveadventure.service.impl;

import com.example.caveadventure.dao.UserMapper;
import com.example.caveadventure.entity.UserEntity;
import com.example.caveadventure.service.UserService;
import com.example.caveadventure.service.ex.InsertException;
import com.example.caveadventure.service.ex.PasswordNotMatchException;
import com.example.caveadventure.service.ex.UserNotFoundException;
import com.example.caveadventure.service.ex.UsernameDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 处理登录注册业务逻辑实现类
 */
@Service
public class UserServiceImpl implements UserService {
    /* 用户成员变量  */
    @Autowired
    private UserMapper usermapper;

    /**
     * 实现登录功能
     * @param username 用户名
     * @param password 用户密码
     * @return 用户
     */
    @Override
    public UserEntity login(String username, String password){

        UserEntity res = usermapper.findByUsername(username);

        // 用户不存在
        if(res == null){
            throw new UserNotFoundException("用户数据不存在！");
        }
        // 判断密码是否正确
        String userPwd = res.getPwd();
        if(!userPwd.equals(password)){
            throw new PasswordNotMatchException("用户密码错误！");
        }
        // 返回查找的用户
        return res;
    }

    /**
     * 实现注册功能
     * @param user 注册用户
     */
    @Override
    public void reg(UserEntity user) {
        // 判断用户名是否已被使用
        String username = user.getUsername();
        UserEntity res = usermapper.findByUsername(username);
        if(res != null){
            throw new UsernameDuplicateException("尝试注册的用户名[" + username + "]已经被占用!");
        }

        // 判断是否正常插入数据
        Integer rows = usermapper.insert(user);
        if(rows != 1){
            throw new InsertException("添加数据出现异常！请联系开发人员！");
        }
    }
}
