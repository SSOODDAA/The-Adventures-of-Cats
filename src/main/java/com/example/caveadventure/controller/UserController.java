package com.example.caveadventure.controller;

import com.example.caveadventure.entity.UserEntity;
import com.example.caveadventure.service.UserService;
import com.example.caveadventure.util.JsonResult;
import java.io.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/CaveAdventure/users")
public class UserController extends BaseController{
    @Autowired
    private UserService userservice;

    /* 注册 */
    @PostMapping
    public JsonResult<Void> reg(@RequestBody UserEntity user){
        userservice.reg(user);
        return new JsonResult<>(OK);
    }

    /* 登录 */
    @GetMapping
    public JsonResult<UserEntity> login(@RequestParam String username,@RequestParam String pwd){
        UserEntity data = userservice.login(username, pwd);

        return new JsonResult<UserEntity>(OK, data);
    }

    /* 更新 */
    @PutMapping
    public JsonResult<UserEntity> update(Integer userid, String info){
        userservice.updatePassword(userid, info);

        userservice.updateUsername(userid, info);

        UserEntity data = userservice.findByUserid(userid);
        return new JsonResult<UserEntity>(OK, data);
    }


}
