package com.example.caveadventure.service;

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
        // 打印地图
        for (int i=0; i<res.size(); i++){
            if (res.contains(i) && res.get(5)==i){
                // 魔法房间
                System.out.print("2 ");
            }
            else if(res.contains(i)){
                // 石头
                System.out.print("1 ");
            }
            else {
                // 房间
                System.out.print("0 ");
            }
            if (i%5==0){
                // 换行
                System.out.print("\n");
            }
        }

    }


}
