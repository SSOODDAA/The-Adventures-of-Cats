package com.example.caveadventure.dao;

import com.example.caveadventure.entity.MapEntity;
import com.example.caveadventure.entity.PlayerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 玩家数据库操作
 */
@Repository
public interface PlayerReposity extends MongoRepository<PlayerEntity,Integer> {
    /**
     * 在数据库中增加一条玩家数据，不用实现，使用自带的
     */

    /**
     * 通过用户id获取该玩家游戏信息
     * @param userid 用户id
     * @return 玩家对象的全部信息
     */
    @Query(value = "{'_id':?0}",fields = "{}")
    public PlayerEntity findByUserid(Integer userid);

    /**
     * 修改数据库中用户的游戏信息，不用实现，使用自带的
     */

    /**
     * 通过userid对玩家信息进行删除
     * @param userid 用户id
     * @return 返回数值
     */
    public int deleteByUserid(Integer userid);

}
