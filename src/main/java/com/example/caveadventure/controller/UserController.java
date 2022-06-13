package com.example.caveadventure.controller;

import com.example.caveadventure.entity.UserEntity;
import com.example.caveadventure.service.UserService;
import com.example.caveadventure.util.JsonResult;
import java.io.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/CaveAdventure/users")
public class UserController extends BaseController{
    @Autowired
    private UserService userservice;

    @PostMapping
    public JsonResult<Void> reg(@RequestBody UserEntity user){
        userservice.reg(user);
        return new JsonResult<>(OK);
    }

    @GetMapping
    public JsonResult<UserEntity> login(@RequestParam String username,@RequestParam String pwd){
        UserEntity data = userservice.login(username, pwd);

        return new JsonResult<UserEntity>(OK, data);
    }

}
