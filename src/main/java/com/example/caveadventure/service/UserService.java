package com.example.caveadventure.service;


import com.example.caveadventure.entity.UserEntity;

public interface UserService {
    /**
     * 用户登录功能接口
     * @param username 用户名
     * @param password 用户密码
     * @return 返回user
     */
    UserEntity login(String username, String password);

    void reg(UserEntity user);
}
