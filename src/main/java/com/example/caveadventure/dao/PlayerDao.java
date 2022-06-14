package com.example.caveadventure.dao;

import com.example.caveadventure.entity.PlayerEntity;



public interface PlayerDao {
    /**
     * 当前游戏档初始化
     * @param playerEntity 玩家信息
     * @return 影响行数
     */
    Integer insert(PlayerEntity playerEntity);
}
