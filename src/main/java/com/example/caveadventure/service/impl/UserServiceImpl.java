package com.example.caveadventure.service.impl;

import com.example.caveadventure.dao.UserMapper;
import com.example.caveadventure.entity.UserEntity;
import com.example.caveadventure.service.UserService;
import com.example.caveadventure.service.ex.*;
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
        // 用户不存在
        UserEntity res = usermapper.findByUsername(username);
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

    /**
     * 修改用户名
     * @param userid id
     * @param username 更新后用户名
     */
    @Override
    public void updateUsername(Integer userid, String username){
        // 判断更改后的用户名是否已被使用
        UserEntity res = usermapper.findByUsername(username);
        if(res != null){
            throw new UsernameDuplicateException("更新后用户名[" + username + "]已经被占用!");
        }

        // 依据userid更新用户名
        Integer rows = usermapper.updateUsernameByUserid(userid, username);
        if(rows != 1){
            throw new UpdateException("用户名更新出现异常！请联系开发人员！");
        }
    }

    @Override
    public void updatePassword(Integer userid, String pwd){
        // 依据userid更新密码
        Integer rows = usermapper.updatePasswordByUserid(userid, pwd);
        if(rows != 1){
            throw new UpdateException("密码更新出现异常！请联系开发人员！");
        }
    }

    @Override
    public UserEntity findByUserid(Integer userid){
        return usermapper.findByUserid(userid);
    }

}
