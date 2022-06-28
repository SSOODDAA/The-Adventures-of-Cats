package com.example.caveadventure.controller;

import com.example.caveadventure.entity.PlayerEntity;
import com.example.caveadventure.entity.ProductEntity;
import com.example.caveadventure.service.MapService;
import com.example.caveadventure.service.ex.ServiceException;
import com.example.caveadventure.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 这里的映射写法尚且存疑
 */
@RestController
@RequestMapping("/CaveAdventure/game")
public class MapController extends BaseController{
    @Autowired
    private MapService mapService;

    /**
     * 默认直接触发，初始化游戏基本配置：地图
     * @param userid 用户id
     * @return 死房间与魔法房间的索引
     */
    @RequestMapping("/")    // 考虑可以修改为Post请求
    public JsonResult<List<Integer>> initGame(Integer userid){
        mapService.init(userid);

        // 首先查询石头，然后查询魔法房间
        List<Integer> res = mapService.findDeadRoom(userid);
        int magicRoom = mapService.findMagicRoom(userid);
        res.add(magicRoom);

        return new JsonResult<List<Integer>>(OK, res);
    }

    /**
     * 人物移动上下左右事件与刷NPC
     * 需要对上下左右移动有编号，传入编号。如这里，上下左右，1234
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
    @GetMapping("/handbook")
    public JsonResult<List<ProductEntity>> handbook(){
        return new JsonResult<>(OK, mapService.handbook());
    }


}
