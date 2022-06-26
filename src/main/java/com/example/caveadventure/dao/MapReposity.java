package com.example.caveadventure.dao;

import com.example.caveadventure.entity.MapEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 地图数据库操作
 */
@Repository
public interface MapReposity extends MongoRepository<MapEntity,Integer> {
    /**
     * 在数据库中增加一条地图数据，不用实现，使用自带的
     */

    /**
     * 通过用户id获取该用户游戏地图信息
     * @param userid
     * @return
     */
    @Query(value = "{'_id':?0}",fields = "{}")
    public MapEntity findByUserid(Integer userid);

    /**
     * 在数据库中修改一条地图数据，不用实现，使用自带的
     */

    /**
     * 通过userid对用户地图信息进行删除
     * @param userid
     * @return
     */
    public int deleteByUserid(Integer userid);

}
