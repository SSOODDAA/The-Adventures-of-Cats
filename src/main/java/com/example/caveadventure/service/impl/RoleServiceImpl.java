package com.example.caveadventure.service.impl;

import com.example.caveadventure.dao.PlayerReposity;
import com.example.caveadventure.dao.RoleMapper;
import com.example.caveadventure.entity.PlayerEntity;
import com.example.caveadventure.entity.ProductEntity;
import com.example.caveadventure.entity.RoleEntity;
import com.example.caveadventure.service.RoleService;
import com.example.caveadventure.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RoleServiceImpl implements RoleService {
    /**
     * 玩家信息数据库与角色数据库
     */
    @Autowired
    private PlayerReposity playerreposity;
    @Autowired
    private RoleMapper rolemapper;

    /**
     * 选择角色功能实现
     * @param roleid 选择角色id
     * @param userid 用户id
     */
    @Override
    public void selectRole(Integer roleid, Integer userid){
        // 获取角色信息
        RoleEntity role = rolemapper.findByRoleid(roleid);
        // 创建玩家
        PlayerEntity player = new PlayerEntity();
        player.setUserid(userid);
        player.setRoleid(role.getId());
        player.setAdventure(role.getAdventure());
        player.setBaglimit(role.getBaglimit());
        // 初始容量设置为背包上限
        player.setBagweight(role.getBaglimit());
        player.setHeart(role.getHeart());
        // player.setProduct(new ArrayList<ProductEntity>());
        // 设置物品栏默认为空
        player.setProduct(null);
        // 设置游戏时长上限5min
        player.setEndtime(300);

        // 插入记录
        try{
            playerreposity.save(player);
        } catch (Exception e){
            throw new InsertException("选择角色时出现异常！");
        }

    }


    /**
     * 返回角色血量上限
     * @param roleid 用户id
     * @return 血量上限
     */
    @Override
    public int getHeartLimit(Integer roleid){
        return rolemapper.findByRoleid(roleid).getHeart();
    }



}
