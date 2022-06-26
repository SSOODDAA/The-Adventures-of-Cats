package com.example.caveadventure.service;

import java.util.List;

public interface MapService {

    /* 初始化地图 */
    List<Integer> init(Integer userid);
    /* 生成若干随机数 */
    List<Integer> randSeveralNums(int len, int start, int end, List<Integer> sub);
    /* 判断图连通性 */
    boolean judgeConnected(int[][] map);
    /* 深搜 */
    int dfs(int[][] map,boolean[][] visited,int x,int y);

}
