package com.example.caveadventure.service.impl;

import com.example.caveadventure.dao.MapReposity;
import com.example.caveadventure.dao.PlayerReposity;
import com.example.caveadventure.dao.ProductMapper;
import com.example.caveadventure.entity.MapEntity;
import com.example.caveadventure.entity.PlayerEntity;
import com.example.caveadventure.entity.ProductEntity;
import com.example.caveadventure.service.MapService;
import com.example.caveadventure.service.ex.ServiceException;
import com.example.caveadventure.service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.RouteMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MapServiceImpl implements MapService {
    /* 地图操作 */
    @Autowired
    public MapReposity mapReposity;
    /* 玩家操作 */
    @Autowired
    public PlayerReposity playerReposity;
    /* 查物品 */
    @Autowired
    public ProductMapper productMapper;


    /**
     * 初始化游戏地图
     * @param userid 用户id
     * @return 地图石头坐标
     */
    public int[] init(Integer userid){
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
        mapReposity.save(mapEntity);   // 更改

        // 生成返回值
        int[][] resMap = new int[5][5];
        for (int i=0; i<res.size(); i++){
            int index = res.get(i);
            if(i == res.size()-1){
                // 魔法房间为list最后一个元素
                resMap[index/5][index%5] = 2;
            }
            else {
                resMap[index/5][index%5] = 1;
            }
        }

        int[] finalMap = new int[25];
        for (int i=0; i<5; i++){
            System.arraycopy(resMap[i], 0, finalMap, 5 * i + 0, 5);
        }

        return finalMap;
    }


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
        System.out.println("查找失败");
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

    /**
     * 人物移动
     * @param userid 用户id
     * @param action 具体移动操作
     */
    public void move(Integer userid, Integer action){
        // 查找玩家，在后边可以让它回退扣血，更新信息
        PlayerEntity player = playerReposity.findByUserid(userid);

        // 查找老地图，更新为新地图
        MapEntity oldMap = mapReposity.findByUserid(userid);
        MapEntity mapEntity = new MapEntity();
        // 获取当前位置
        int nowroomx = oldMap.getNowroomx();
        int nowroomy = oldMap.getNowroomy();

        // 更新的参数：当前位置
        if (action == 1){
            nowroomx -= 1;  // 上
        }else if (action == 2){
            nowroomx += 1;  // 下
        }else if (action == 3){
            nowroomy -= 1;  // 左
        }else if (action == 4){
            nowroomy += 1;  // 右
        }
        // 获取死房间与魔法房间
        List<Integer> route = oldMap.getRoute();
        List<Integer> deads = oldMap.getDeadroom();
        int magicRoom = oldMap.getMagicroom();
        int curPos = 5*nowroomx + nowroomy;
        // 检测合法性
        if (nowroomx < 0 || nowroomx > 4 || nowroomy < 0 || nowroomy > 4 || deads.contains(curPos)){
            nowroomx = oldMap.getNowroomx();
            nowroomy = oldMap.getNowroomy();
        }else {
            // 魔法房间随机刷位置
            if (curPos == magicRoom){
                Random rand = new Random();
                int magicPos = rand.nextInt(25);
                while(deads.contains(magicPos)){
                    magicPos = rand.nextInt(25);
                }
                nowroomx = magicPos / 5;
                nowroomy = magicPos % 5;
            }

            // 更新的参数：路径，把老点加进去
            int pos = 5*oldMap.getNowroomx()+ oldMap.getNowroomy();
            if(route == null){
                route = new ArrayList<Integer>();
            }
            else {
                // 若是回到上一步位置，则需要扣血 ---  如果没有back这个按钮那么魔法房间是无法保证能够回退的---------   ！！注意！！
                int lastRoom = route.get(route.size() - 1);
                if(nowroomx == lastRoom/5 && nowroomy == lastRoom%5){
                    player.setHeart(player.getHeart() - 5);
                }

                route.add(pos);
            }
        }

        // 存储更新后的值
        mapEntity.setUserid(userid);
        mapEntity.setNowroomx(nowroomx);
        mapEntity.setNowroomy(nowroomy);
        mapEntity.setRoute(route);
        mapEntity.setMagicroom(oldMap.getMagicroom());
        mapEntity.setDeadroom(oldMap.getDeadroom());
        mapReposity.save(mapEntity);

        // 更新玩家信息
        playerReposity.save(player);
    }


    /**
     * 刷NPC事件
     * @param userid 用户id
     * @return NPC生成情况以及人物是否还活着
     */
    public List<Integer> randNPC(Integer userid){
        // 0,1,2分别代表坏NPC，无NPC，好NPC状态
        Random rand = new Random();
        int npc = rand.nextInt(3);

        // 玩家
        PlayerEntity player = playerReposity.findByUserid(userid);
        int oldHeart = player.getHeart();
        // 依据npc情况更新血量
        if(npc == 0){
            player.setHeart(Math.max(0, oldHeart - 10));
        }else if(npc == 2){
            player.setHeart(Math.min(100, oldHeart + 10));
        }
        playerReposity.save(player);

        // 结果返回两个元素，第一个表示npc类别，第二个表示角色的血量
        List<Integer> res = new ArrayList<>();
        res.add(npc);
        res.add(player.getHeart());
        return res;
    }


    /**
     * 查看房间物品
     * @param userid 用户id
     * @return 当前房间物品信息
     */
    public List<ProductEntity> look(Integer userid){
        // 这里将adventure视为生成物品的数量相关
        PlayerEntity player = playerReposity.findByUserid(userid);
        double num = player.getAdventure() * 5;

        // 生成物品
        List<ProductEntity> res = new ArrayList<>();
        Random rand = new Random();
        for (int i=1; i<=num; i++){
            // 取高斯分布的右半侧，递减
           double productId = Math.abs(rand.nextGaussian() * 5);
           int index = (int) productId;
           ProductEntity productEntity = productMapper.findById(index);
           res.add(productEntity);
        }

        return res;
    }

    /**
     * 从房间拿物品
     * @param userid 用户id
     * @param index 被拿物品在物品栏中的序号
     * @return 更新后的背包（即物品栏）
     */
    public List<ProductEntity> take(Integer userid, List<Integer> index, List<ProductEntity> products){
        // 获取角色信息
        PlayerEntity player = playerReposity.findByUserid(userid);
        List<ProductEntity> bag = player.getProduct();
        int newWeight = player.getBagweight();

        // 注意这里有个次序问题！！！
        for (int i : index){
            ProductEntity curProduct = products.get(i);
            int curProductWeight = curProduct.getWeight();
            if (newWeight - curProductWeight >= 0){
                bag.add(curProduct);
                newWeight -= curProductWeight;
            }else {
                throw new ServiceException("加入背包物品过多已超过背包容量！");
            }
        }
        // 更新存入数据库
        player.setBagweight(newWeight);
        player.setProduct(bag);
        playerReposity.save(player);

        return bag;
    }

    /**
     * 丢弃物品栏中选中的物品
     * @param userid 用户id
     * @param index 选中的物品
     * @return 丢弃完毕后物品栏信息
     */
    public List<ProductEntity> drop(Integer userid, List<Integer> index){
        // 获取角色信息
        PlayerEntity player = playerReposity.findByUserid(userid);
        List<ProductEntity> bag = player.getProduct();
        int newWeight = player.getBagweight();
        // 删除选中的物品
        for (int i : index){
            newWeight -= bag.get(i).getWeight();
            bag.remove(i);
        }
        // 更新存入数据库
        player.setBagweight(newWeight);
        player.setProduct(bag);
        playerReposity.save(player);

        return bag;
    }

    /**
     * 返回背包当前容量
     * @param userid 用户id
     * @return 背包当前容量
     */
    public int items(Integer userid){
        return playerReposity.findByUserid(userid).getBagweight();
    }


    /**
     * 查询角色当前状态
     * @param userid 用户id
     * @return list(血量,背包容量,分数)
     */
    public List<Integer> queryStates(Integer userid){
        PlayerEntity player = playerReposity.findByUserid(userid);
        // 获取血量
        int heart = player.getHeart();
        // 获取背包容量
        int bagWeight = player.getBagweight();
        // 获取分数
        int score = 0;
        if (player.getProduct() != null){
            // 直接拿稀有度折算为分数
            for (ProductEntity p :  player.getProduct()){
                score += p.getRare();
            }
            // 随便配的系数，后边可以改 >_<
            score += player.getHeart() * 2;
        }
        // 返回
        List<Integer> res = new ArrayList<>();
        res.add(heart);
        res.add(bagWeight);
        res.add(score);

        return res;
    }



    /**
     * 点击图鉴查询所有物品与NPC信息
     * @return 所有信息
     */
    public List<ProductEntity> handbook(){
        List<ProductEntity> book = new ArrayList<>();

        // 查询所有物品
        for(int i=1; i<=12; i++){
            book.add(productMapper.findById(i));
        }
        return book;
    }





}
