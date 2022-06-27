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
    /*查找玩家当前所处的房间位置*/
    int[] findNowRoom(Integer userid);
    /*查找玩家从游戏开始到当前所经历的路径*/
    List<Integer> findRoute(Integer userid);
    /*查找地图中所有死房间的位置*/
    List<Integer> findDeadRoom(Integer userid);
    /*查找地图中的魔法房间位置*/
    int findMagicRoom(Integer userid);



}
