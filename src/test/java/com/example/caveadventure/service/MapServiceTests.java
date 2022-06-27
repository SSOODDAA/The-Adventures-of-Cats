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
        List<Integer> res = mapService.init(userid);
        int[][] map = new int[5][5];

        //生成地图
        for (int i=0; i<res.size(); i++){
            int index = res.get(i);
            if(i == res.size()-1){
                // 魔法房间为list最后一个元素
                map[index/5][index%5] = 2;
            }
            else {
                map[index/5][index%5] = 1;
            }
        }

        // 打印地图
        for (int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                System.out.print(map[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
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
        System.out.println("死房间:"+dead);
        int magic= mapService.findMagicRoom(userid);
        System.out.println("魔法房间："+magic);
    }

    /**
     * 测试人物移动功能
     */
    @Test
    public void move(){
        int userid=12;
        System.out.println("向上移动：");
        mapService.move(userid, 1);

        int[] nowRoom = mapService.findNowRoom(userid);
        System.out.println("向上移动后坐标：("+nowRoom[0]+","+nowRoom[1]+")");
        List<Integer> route = mapService.findRoute(userid);
        System.out.println("向上移动后路径："+route);
    }



}
