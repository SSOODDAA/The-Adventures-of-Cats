package com.example.caveadventure.service.impl;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MapServiceImpl {
    /* 初始化地图 */
    public void init(Integer userid){
        // 初始化数组地图，其中0表示房间，1表示石头
        int[][] map = new int[5][5];
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                map[i][j] = 0;
            }
        }




    }

    /*  */
    public List<Integer> randSeveralNums(int len, int start, int end){
        List<Integer> list = new ArrayList<Integer>();

        Random rand = new Random();
        while(list.size() != len){
            int num = rand.nextInt(end - start) + start;

        }


        return list;
    }



}
