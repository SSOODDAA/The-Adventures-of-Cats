package com.example.caveadventure.controller;

import com.example.caveadventure.entity.UserEntity;
import com.example.caveadventure.service.UserService;
import com.example.caveadventure.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("CaveAdventure/users")
public class UserController extends BaseController{
    @Autowired
    private UserService userservice;

    @RequestMapping("reg")
    public JsonResult<Void> reg(UserEntity user){
        userservice.reg(user);
        return new JsonResult<>(OK);
    }

    @RequestMapping("login")
    public JsonResult<UserEntity> login(String username, String password, HttpSession session){
        UserEntity data = userservice.login(username, password);

        session.setAttribute("uid", data.getUserid());
        session.setAttribute("username", data.getUsername());

        return new JsonResult<UserEntity>(OK, data);
    }



}
