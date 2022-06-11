package com.example.caveadventure.service;

import com.example.caveadventure.entity.UserEntity;
import com.example.caveadventure.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserService userservice;

    @Test
    public void reg(){
        try{
            UserEntity user = new UserEntity();
            user.setUsername("doge");
            user.setPwd("ddooggee");
            user.setBest(123);
            userservice.reg(user);
            System.out.println("注册成功！");
        } catch (ServiceException e){
            System.out.println("注册失败！"+e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login(){
        try{
            String username = "doge";
            String password = "ddooggee";
            UserEntity user = userservice.login(username, password);
            System.out.println("登录成功！");
        } catch (ServiceException e){
            System.out.println("登录失败！"+e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }


}
