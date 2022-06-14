package com.example.caveadventure.dao;

import com.example.caveadventure.entity.PlayerEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PlayerDaoTests {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void insert()
    {
        PlayerEntity playerEntity=new PlayerEntity();
        playerEntity.setUserid(2);
        playerEntity.setRoleid(1);
        playerEntity.setAdventure(1.00);
        playerEntity.setEndtime(300);
        playerEntity.setBaglimit(100);
        playerEntity.setHeart(100);
        playerEntity.setProduct(null);
        mongoTemplate.insert(playerEntity);
        System.out.println("ok");
    }

}
