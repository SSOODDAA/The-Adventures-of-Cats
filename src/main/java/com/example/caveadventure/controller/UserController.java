package com.example.caveadventure.controller;

import com.example.caveadventure.entity.UserEntity;
import com.example.caveadventure.service.UserService;
import com.example.caveadventure.service.ex.UpdateException;
import com.example.caveadventure.service.impl.UserServiceImpl;
import com.example.caveadventure.util.JsonResult;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("CaveAdventure")
public class UserController extends BaseController{
    @Autowired
    private UserService userservice;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/user")
    public JsonResult<Void> reg(UserEntity user){     ////// 存疑
        userservice.reg(user);
        return new JsonResult<>(OK);
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @param request
     * @return
     */
    @GetMapping("/user/{username}")
    public JsonResult<UserEntity> login(@PathVariable @RequestParam("username") String username,
                                        @RequestParam("password")  String password, HttpServletRequest request){
        UserEntity user = userservice.login(username, password);

        request.getSession().setAttribute("userid", user.getUserid());  // 存储账号，密码，最高分。便于展示
        request.getSession().setAttribute("username", user.getUsername());
        request.getSession().setAttribute("best", user.getBest());

        return new JsonResult<UserEntity>(OK, user);
    }

    /**
     * 更新用户信息
     * @param info
     * @param option
     * @param request
     * @return
     */
    @PutMapping("/user")
    public JsonResult<Void> update(String info, String option, HttpServletRequest request){
        Integer userid = (Integer) request.getSession().getAttribute("userid");

        try {
            if (option.equals("username")){
                userservice.updateUsername(userid, info);

                // 重新将更新值存储在session中
                UserEntity user = userservice.findByUserid(userid);
                request.getSession().setAttribute("username", user.getUsername());
            }
            else if (option.equals("password")){
                userservice.updatePassword(userid, info);
            }
        } catch (Exception e){
            throw new UpdateException("更新信息错误！");
        }

        return new JsonResult<Void>(OK);
    }


}
