package com.example.caveadventure.service;

import com.example.caveadventure.service.impl.MapServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 游戏地图初始化功能测试
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapServiceTests {
    @Autowired
    public MapService mapService;

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

    @Test
    public void npc(){
        int userid = 12;
        List<Integer> randNpc = mapService.randNPC(userid);
        System.out.println("刷npc的情况为："+randNpc.get(0));
        System.out.println("当前的血量为："+randNpc.get(1));

        // 查数据库说明血量
    }

    @Test
    public void handbook(){
        System.out.println("图鉴内容为：");
        System.out.println(mapService.handbook());
    }

}
