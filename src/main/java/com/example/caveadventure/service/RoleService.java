package com.example.caveadventure.service;


public interface RoleService {

    /**
     * 选择角色功能接口
     * @param roleid 选择角色id
     * @param userid 用户id
     */
    void selectRole(Integer roleid, Integer userid);

}
