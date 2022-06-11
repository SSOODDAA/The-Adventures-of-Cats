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

    /**
     * 用户注册功能接口
     * @param user 用户
     */
    void reg(UserEntity user);

    /**
     * 更新用户名
     * @param userid id
     * @param username 更新后用户名
     */
    void updateUsername(Integer userid,String username);

    /**
     * 更改密码
     * @param userid  id
     * @param pwd 密码
     */
    void updatePassword(Integer userid,String pwd);

    /**
     * 查找用户
     * @param userid id
     * @return user
     */
    UserEntity findByUserid(Integer userid);
}
