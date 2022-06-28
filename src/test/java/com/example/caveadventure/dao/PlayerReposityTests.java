package com.example.caveadventure.dao;

import com.example.caveadventure.entity.PlayerEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PlayerReposityTests {

    @Autowired
    private PlayerReposity playerReposity;
    @Test
    public void insert()
    {
        PlayerEntity playerEntity=new PlayerEntity();
        playerEntity.setUserid(8);
        playerEntity.setRoleid(1);
        playerEntity.setBagweight(0);
        playerEntity.setAdventure(1.00);
        playerEntity.setEndtime(300);
        playerEntity.setBaglimit(100);
        playerEntity.setHeart(100);
        playerEntity.setProduct(null);
        PlayerEntity result=playerReposity.insert(playerEntity);
        if(result!=null){
            System.out.println(result.toString());
        } else{
            System.out.println("插入失败");
        }
    }

    @Test
    public void find()
    {
        PlayerEntity result=playerReposity.findByUserid(7);
        if(result!=null){
            System.out.println(result.toString());
        } else{
            System.out.println("查找失败");
        }
    }

    @Test
    public void update()
    {
        PlayerEntity playerEntity=new PlayerEntity();
        playerEntity.setUserid(3);
        playerEntity.setBagweight(0);
        playerEntity.setRoleid(1);
        playerEntity.setAdventure(1.00);
        playerEntity.setEndtime(300);
        playerEntity.setBaglimit(100);
        playerEntity.setHeart(200);
        playerEntity.setProduct(null);
        PlayerEntity result=playerReposity.save(playerEntity);
        if(result!=null){
            System.out.println(result.toString());
        } else{
            System.out.println("更新失败");
        }
    }

    @Test
    public void delete()
    {
        int flag;
        flag=playerReposity.deleteByUserid(6);
        System.out.println(flag);
    }

}
