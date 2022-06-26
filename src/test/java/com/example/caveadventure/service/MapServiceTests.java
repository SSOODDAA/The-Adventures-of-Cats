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
}
