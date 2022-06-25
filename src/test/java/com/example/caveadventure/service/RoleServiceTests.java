package com.example.caveadventure.service;

import com.example.caveadventure.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 玩家选择游戏角色测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleServiceTests {
    @Autowired
    private RoleService roleservice;

    @Test
    public void selectRole(){
        try{
            Integer userid = 3;
            Integer roleid = 1;
            roleservice.selectRole(roleid, userid);
        }catch (ServiceException e){
            System.out.println("选择角色失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

}
