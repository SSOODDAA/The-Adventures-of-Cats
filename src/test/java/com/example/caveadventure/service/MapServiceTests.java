package com.example.caveadventure.service;

import com.example.caveadventure.entity.ProductEntity;
import com.example.caveadventure.service.impl.MapServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 游戏地图初始化功能测试
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapServiceTests {
    @Autowired
    public MapService mapService;
    @Autowired
    public PlayerService playerService;

    /**
     * 测试初始化地图功能
     */
    @Test
    public void initMap(){
        int userid = 12;
        int[] res = mapService.init(userid);

        for (int i=0; i<res.length;i++){
            System.out.print(res[i]);
            System.out.print(" ");
            if (i % 5 == 0){
                System.out.print("\n");
            }
        }

    }

    /**
     * 测试查找功能
     */
    @Test
    public void find()
    {
        int userid=12;
        int[] nowRoom=mapService.findNowRoom(userid);
        System.out.println("当前所处房间:"+nowRoom[0]+","+nowRoom[1]);
        List<Integer> route=mapService.findRoute(userid);
        System.out.println("路径:"+route);
        List<Integer> dead=mapService.findDeadRoom(userid);
        System.out.println("死房间为:"+dead);
        int magic= mapService.findMagicRoom(userid);
        System.out.println("魔法房间为："+magic);
    }

    /**
     * 测试人物移动功能
     */
    @Test
    public void move(){
        int userid=12;
        System.out.println("向下移动：");
        mapService.move(userid, 2);

        int[] nowRoom = mapService.findNowRoom(userid);
        System.out.println("向下移动后坐标：("+nowRoom[0]+","+nowRoom[1]+")");
        List<Integer> route = mapService.findRoute(userid);
        System.out.println("向下移动后路径："+route);
    }

    /**
     * 刷出npc测试
     */
    @Test
    public void npc(){
        int userid = 12;
        List<Integer> randNpc = mapService.randNPC(userid);
        System.out.println("刷npc的情况为："+randNpc.get(0));
        System.out.println("当前的血量为："+randNpc.get(1));

        // 查数据库说明血量
    }

    /**
     * 查看图鉴测试
     */
    @Test
    public void handbook(){
        System.out.println("图鉴内容为：");
        System.out.println(mapService.handbook());
    }

    /**
     * 查看当前房间刷出物品信息测试
     */
    @Test
    public void look(){
        int userid = 12;
        List<ProductEntity> products = mapService.look(userid);

        System.out.println("随机刷出的物品为：");
        System.out.println(products);
    }

    /**
     * 拿物品测试
     */
    @Test
    public void take(){
        int userid = 12;
        // 查询老背包状态
        List<ProductEntity> oldBag = playerService.findProduct(userid);
        System.out.println("拿东西前老背包内容为：");
        System.out.println(oldBag);
        // 随机刷出物品
        List<ProductEntity> products = mapService.look(userid);
        System.out.println("随机刷出的物品为：");
        System.out.println(products);
        // 测试，选择第一个物品
        List<Integer> choice = new ArrayList<>();
        choice.add(0);
        // 返回拿完物品后的背包
        mapService.take(userid, choice, products);
        System.out.println("拿东西后新背包内容为：");
        System.out.println(playerService.findProduct(userid));
    }

    /**
     * 丢弃物品测试
     */
    @Test
    public void drop(){
        int userid = 12;
        // 查询老背包状态
        List<ProductEntity> oldBag = playerService.findProduct(userid);
        System.out.println("老背包内容为：");
        System.out.println(oldBag);
        // 测试，丢掉第一个物品
        List<Integer> choice = new ArrayList<>();
        choice.add(0);
        // 返回拿完物品后的背包
        mapService.drop(userid, choice);
        System.out.println("新背包内容为：");
        System.out.println(playerService.findProduct(userid));
    }

    /**
     * 背包容量测试
     */
    @Test
    public void items(){
        int userid = 12;
        System.out.println("背包容量为：");
        System.out.println(mapService.items(userid));
    }

    /**
     * 角色状态测试
     */
    @Test
    public void queryStates(){
        int userid = 12;
        List<Integer> states = mapService.queryStates(userid);
        System.out.println("角色当前的状态为（血量、背包容量、分数）：");
        System.out.println(states);
    }
}
