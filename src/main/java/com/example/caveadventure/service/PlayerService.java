package com.example.caveadventure.service;

import com.example.caveadventure.entity.ProductEntity;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.util.List;

public interface PlayerService {

    /*查找当前玩家的生命值*/
    int findHeart(Integer userid);
    /*查找当前玩家背包上限*/
    int findBagLimit(Integer userid);
    /*查找当前玩家背包容量*/
    int findBagWeight(Integer userid);
    /*查找当前玩家的奇遇值*/
    double findAdventure(Integer userid);
    /*查找当前玩家的物品栏*/
    List<ProductEntity> findProduct(Integer userid);
    /*查找当前玩家剩余时间*/
    int findEndTime(Integer userid);
}
