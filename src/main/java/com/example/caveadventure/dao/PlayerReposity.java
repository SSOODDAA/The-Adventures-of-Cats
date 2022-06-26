package com.example.caveadventure.dao;

import com.example.caveadventure.entity.PlayerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 玩家数据库操作
 */
@Repository
public interface PlayerReposity extends MongoRepository<PlayerEntity,Integer> {
    /**
     * 在数据库中增加一条玩家数据，不用实现，使用自带的
     */

}
