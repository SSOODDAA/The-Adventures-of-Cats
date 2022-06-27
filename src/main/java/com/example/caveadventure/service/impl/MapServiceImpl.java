package com.example.caveadventure.service.impl;

import com.example.caveadventure.dao.MapReposity;
import com.example.caveadventure.entity.MapEntity;
import com.example.caveadventure.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MapServiceImpl implements MapService {
    @Autowired
    public MapReposity mapReposity;

    /**
     * 初始化游戏地图
     * @param userid 用户id
     * @return 地图石头坐标
     */
    public List<Integer> init(Integer userid){
        // 初始化数组地图，0表示房间
        int[][] map = new int[5][5];
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                map[i][j] = 0;
            }
        }
        // 判断连通性
        List<Integer> stones;
        do {
            stones = randSeveralNums(5, 0, 24, null);
            // 1表示石头
            for(int i : stones){
                map[i/5][i%5] = 1;
            }
        }while(!judgeConnected(map));

        // 2代表魔法房间
        List<Integer> magic = randSeveralNums(1, 0, 24, stones);
        int magicRoom = magic.get(magic.size()-1);

        map[magicRoom/5][magicRoom%5] = 2;

        // 依次返回石头与魔法房间的序号
        List<Integer> res = new ArrayList<Integer>();
        res.addAll(stones);
        res.addAll(magic);

        // 存储地图
        MapEntity mapEntity = new MapEntity();
        mapEntity.setUserid(userid);
        mapEntity.setDeadroom(stones);
        mapEntity.setMagicroom(magicRoom);
        mapEntity.setNowroomx(0);
        mapEntity.setNowroomy(0);
        mapEntity.setRoute(null);
        mapReposity.insert(mapEntity);
        return res;
    }

    /**
     *
     */





    /**
     * 生成len个范围在[start, end]的随机数
     * @param len 生成随机数个数
     * @param start 下界
     * @param end 上界
     * @return 随机数列表
     */
    public List<Integer> randSeveralNums(int len, int start, int end, List<Integer> sub){
        List<Integer> list = new ArrayList<Integer>();
        // 排除已经添加项
        if (sub != null){
            list.addAll(sub);
            len += list.size();
        }

        Random rand = new Random();
        while(list.size() != len){
            int num = rand.nextInt(end - start) + start;
            if(!list.contains(num)){
                list.add(num);
            }
        }

        return list;
    }

    /**
     * 判断地图连通性
     * @param map 地图
     * @return 是否连通
     */
    public boolean judgeConnected(int[][] map){
        if(map == null || map.length == 0) return false;
        int ans = 0;
        // 是否已访问过当前节点
        boolean[][] visited = new boolean[map.length][map[0].length];
        for(int i=0; i<map.length; i++){
            for (int j=0; j<map[0].length; j++){
                if(map[i][j] == 0 && !visited[i][j]){
                    ans += dfs(map, visited, i, j);
                }
            }
        }
        return ans == 1;
    }

    /**
     * 搜索
     * @param map 地图
     * @param visited 辅助数组
     * @param x 当前坐标x
     * @param y 当前坐标y
     * @return 连通区域个数
     */
    public int dfs(int[][] map, boolean[][] visited, int x, int y){
        int[][] directions = {{1,-1,0,0},{0,0,1,-1}};

        if (x<0 || x>= map.length || y<0 || y>=map[0].length) return 0;
        visited[x][y] = true;
        for (int i=0; i<4; i++){
            int xx = x + directions[0][i];
            int yy = y + directions[1][i];
            if (xx>=0 && xx< map.length && yy>=0 && yy < map[0].length && !visited[xx][yy] && map[xx][yy] == 0){
                visited[xx][yy] = true;
                dfs(map, visited, xx, yy);
            }
        }
        return 1;
    }
    /**
     * 查找当前房间位置
     * @param userid 用户id
     * @return 当前房间位置(x,y)坐标
     */
    public int[] findNowRoom(Integer userid) {
        MapEntity mapEntity=mapReposity.findByUserid(userid);
        if (mapEntity!=null)
        {
            int[] nowRoom=new int[2];
            nowRoom[0]=mapEntity.getNowroomx();
            nowRoom[1]=mapEntity.getNowroomy();
            return nowRoom;
        }
        System.out.println("查找失败！");
        return null;
    }

    /**
     * 查找玩家从游戏开始到当前所经历的路径
     * @param userid 用户id
     * @return 路径
     */
    public List<Integer> findRoute(Integer userid)
    {
        MapEntity mapEntity=mapReposity.findByUserid(userid);
        if (mapEntity!=null)
        {
            return mapEntity.getRoute();
        }
        System.out.println("查找失败！");
        return null;
    }

    /**
     * 查找地图中所有死房间的位置
     * @param userid 用户id
     * @return 死房间
     */
    public List<Integer> findDeadRoom(Integer userid)
    {
        MapEntity mapEntity=mapReposity.findByUserid(userid);
        if (mapEntity!=null)
        {
            return mapEntity.getDeadroom();
        }
        System.out.println("查找失败！");
        return null;
    }

    /**
     * 查找地图中的魔法房间位置
     * @param userid 用户id
     * @return 地图中的魔法房间
     */
    public int findMagicRoom(Integer userid) {
        MapEntity mapEntity=mapReposity.findByUserid(userid);
        if (mapEntity!=null)
        {
            return mapEntity.getMagicroom();
        }
        System.out.println("查找失败！");
        return 0;
    }
}
