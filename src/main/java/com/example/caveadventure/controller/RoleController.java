package com.example.caveadventure.controller;

import com.example.caveadventure.service.RoleService;
import com.example.caveadventure.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 选择角色Controller类
 */
@RestController
@RequestMapping("/CaveAdventure/role")
public class RoleController extends BaseController{
    @Autowired
    private RoleService roleservice;

    @PostMapping
    public JsonResult<Void> selectRole(@RequestParam("roleid") Integer roleid, @RequestParam("userid")Integer userid){
        // 选择角色
        roleservice.selectRole(roleid, userid);
        return new JsonResult<Void>(OK);
    }

}
