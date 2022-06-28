package com.example.caveadventure.service;

import com.example.caveadventure.entity.ProductEntity;

import java.util.List;

public interface MapService {

    /* 初始化地图 */
    int[] init(Integer userid);
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

    /* 人物移动 */
    void move(Integer userid, Integer action);

    /* 随机生成事件 */
    /* 刷NPC */
    List<Integer> randNPC(Integer userid);
    /* 刷物品 */
   // List<ProductEntity> randProduct(Integer userid);

    /* 人物行为动作 */
    /* 查看当前房间的物品信息 */
    List<ProductEntity> look(Integer userid);
    /* 从物品栏中丢弃物品 */
    List<ProductEntity> drop(Integer userid, List<Integer> index);
    /* 从房间拿物品 */
    List<ProductEntity> take(Integer userid, List<Integer> index, List<ProductEntity> products);
    /* 返回背包当前容量 */
    int items(Integer userid);

    /* 点击图鉴事件 */
    List<ProductEntity> handbook();

}
