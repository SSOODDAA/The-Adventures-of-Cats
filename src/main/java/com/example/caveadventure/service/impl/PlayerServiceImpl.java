package com.example.caveadventure.service.impl;

import com.example.caveadventure.dao.PlayerReposity;
import com.example.caveadventure.entity.PlayerEntity;
import com.example.caveadventure.entity.ProductEntity;
import com.example.caveadventure.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    /* 玩家操作 */
    @Autowired
    public PlayerReposity playerReposity;

    /**
     * 查找当前玩家的生命值
     * @param userid 用户id
     * @return生命值
     */
    public int findHeart(Integer userid)
    {
        PlayerEntity playerEntity=playerReposity.findByUserid(userid);
        if (playerEntity!=null)
        {
            return playerEntity.getHeart();
        }
        System.out.println("查找失败");
        return 0;
    }
    /**
     * 查找当前玩家的背包上限
     * @param userid 用户id
     * @return 背包上限
     */
    public int findBagLimit(Integer userid) {
        PlayerEntity playerEntity=playerReposity.findByUserid(userid);
        if (playerEntity!=null)
        {
            return playerEntity.getBaglimit();
        }
        System.out.println("查找失败");
        return 0;
    }

    /**
     * 查找当前玩家的背包重量
     * @param userid 用户id
     * @return 背包重量
     */
    public int findBagWeight(Integer userid)
    {
        PlayerEntity playerEntity=playerReposity.findByUserid(userid);
        if (playerEntity!=null)
        {
            return playerEntity.getBagweight();
        }
        System.out.println("查找失败");
        return 0;
    }

    /**
     * 查找当前玩家的奇遇值
     * @param userid 用户id
     * @return 奇遇值
     */
    public double findAdventure(Integer userid)
    {
        PlayerEntity playerEntity=playerReposity.findByUserid(userid);
        if (playerEntity!=null)
        {
            return playerEntity.getAdventure();
        }
        System.out.println("查找失败");
        return 0;
    }

    /**
     * 查找当前玩家物品栏的内容
     * @param userid 用户id
     * @return 物品栏
     */
    public List<ProductEntity> findProduct(Integer userid)
    {
        PlayerEntity playerEntity=playerReposity.findByUserid(userid);
        if (playerEntity!=null)
        {
            return playerEntity.getProduct();
        }
        System.out.println("查找失败");
        return null;
    }

    /**
     * 查找用户结束时间
     * @param userid 用户id
     * @return 结束时间
     */
    public int findEndTime(Integer userid)
    {
        PlayerEntity playerEntity=playerReposity.findByUserid(userid);
        if (playerEntity!=null)
        {
            return playerEntity.getEndtime();
        }
        System.out.println("查找失败");
        return 0;
    }
}
