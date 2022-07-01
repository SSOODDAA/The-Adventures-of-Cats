package com.example.caveadventure.controller;

import com.example.caveadventure.entity.PlayerEntity;
import com.example.caveadventure.entity.ProductEntity;
import com.example.caveadventure.service.MapService;
import com.example.caveadventure.service.PlayerService;
import com.example.caveadventure.service.ex.ServiceException;
import com.example.caveadventure.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 这里的映射写法尚且存疑
 */
@RestController
@RequestMapping("/CaveAdventure/game")
public class MapController extends BaseController{
    @Autowired
    private MapService mapService;
    @Autowired
    private PlayerService playerService;

    /**
     * 默认直接触发，初始化游戏基本配置：地图
     * @param userid 用户id
     * @return 死房间与魔法房间的索引
     */
    @PostMapping
    public JsonResult<int[]> initGame(Integer userid){
        int[]res = mapService.init(userid);

        return new JsonResult<int[]>(OK, res);
    }

    /**
     * 人物移动上下左右事件与刷NPC
     * 需要对上下左右移动有编号，传入编号。如这里，上下左右->1234
     * @param userid 用户id
     * @param action 移动方向
     * @return 移动后坐标
     */
    @PutMapping("/move")
    public JsonResult<List<Integer>> move(Integer userid, Integer action){
        // 更改当前位置并刷npc
        mapService.move(userid, action);
        List<Integer> randNpc = mapService.randNPC(userid);

        if (randNpc.get(1) == 0){
            throw new ServiceException("角色去世了！先这样写！");       // 去世了可能还需要修改
        }
        // 依次返回，刷出的npc情况，当前位置的序号，目前的血量
        int[] now = mapService.findNowRoom(userid);
        List<Integer> res = new ArrayList<>();
        res.add(randNpc.get(0));
        res.add(5*now[0]+now[1]);
        res.add(randNpc.get(1));
        return new JsonResult<List<Integer>>(OK, res);
    }

    /**
     * 查看图鉴
     * @return 所有物品信息
     */
    @GetMapping("/CaveAdventure/handbook")
    public JsonResult<List<ProductEntity>> handbook(){
        return new JsonResult<>(OK, mapService.handbook());
    }

    /**
     * 查看物品栏（背包）
     * @param userid 用户id
     * @return 背包物品
     */
    @GetMapping("queryBag")
    public JsonResult<List<ProductEntity>> queryBag(Integer userid){
        List<ProductEntity> bag = playerService.findProduct(userid);

        return new JsonResult<>(OK, bag);
    }



    /**
     * 查看当前房间随机生成的物品信息
     * @param userid 用户id
     * @return 物品信息
     */
    @PostMapping("/look")
    public JsonResult<List<Integer>> look(Integer userid){
        List<Integer> products = mapService.look(userid);

        return new JsonResult<List<Integer>>(OK, products);
    }

    /**
     * 丢弃物品栏物品
     * @param userid 用户id
     * @param index 选中的丢弃品在物品栏中的序号id
     * @return 更新后的物品栏
     */
    @PutMapping("/drop")
    public JsonResult<List<ProductEntity>> drop(Integer userid, List<Integer> index){
        List<ProductEntity> bag = mapService.drop(userid, index);
        return new JsonResult<List<ProductEntity>>(OK, bag);
    }

    /**
     * 从当前房间拿物品
     * @param userid 用户id
     * @param index 选中的要拿的物品的序号id
     * @return 拿完物品后的背包
     */
    @PostMapping("/take")
    public JsonResult<List<ProductEntity>> take(Integer userid, String index, String products){
        // 强转
        List<Integer> listProducts = Arrays.stream(products.split(",")).map(s->Integer.parseInt(s.trim())).collect(Collectors.toList());
        List<Integer> listIndex = Arrays.stream(index.split(",")).map(s->Integer.parseInt(s.trim())).collect(Collectors.toList());

        List<ProductEntity> bag = mapService.take(userid, listIndex, listProducts);

        return new JsonResult<List<ProductEntity>>(OK, bag);
    }

    /**
     * 查询背包容量
     * @param userid 用户id
     * @return 当前背包容量
     */
    @GetMapping("/items")
    public JsonResult<Integer> items(Integer userid){
        int num = mapService.items(userid);
        return new JsonResult<Integer>(OK, num);
    }

    /**
     * 查询角色状态
     * @param userid 用户id
     * @return list(血量,背包容量,分数)
     */
    @GetMapping("/query")
    public JsonResult<List<Integer>> queryStates(@RequestParam Integer userid){
        List<Integer> states = mapService.queryStates(userid);
        return new JsonResult<List<Integer>>(OK, states);
    }

    /**
     * 使用物品
     * @param userid 用户id
     * @param choice 被选择物品序号
     * @return 使用后物品栏
     */
    @PutMapping("/use")
    public JsonResult<List<ProductEntity>> use(Integer userid, int choice){
        List<ProductEntity> res = mapService.use(userid, choice);

        return new JsonResult<List<ProductEntity>>(OK, res);
    }



}
