package com.example.caveadventure.service;

import com.example.caveadventure.entity.ProductEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerServiceTests {
    @Autowired
    private PlayerService playerService;

    /**
     * 查找功能测试
     */
    @Test
    public void find()
    {
        int userid=8;
        int heart=playerService.findHeart(userid);
        System.out.println("生命值:"+heart);
        int baglimit=playerService.findBagWeight(userid);
        System.out.println("背包上限:"+baglimit);
        int bagweight=playerService.findBagWeight(userid);
        System.out.println("背包当前重量:"+bagweight);
        double adventure=playerService.findAdventure(userid);
        System.out.println("奇遇值："+adventure);
        List<ProductEntity> product=playerService.findProduct(userid);
        System.out.println("物品栏:"+product);
        int endtime=playerService.findEndTime(userid);
        System.out.println("结束时间："+endtime);
    }

}
