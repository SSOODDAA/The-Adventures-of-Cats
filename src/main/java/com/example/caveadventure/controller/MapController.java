package com.example.caveadventure.controller;

import com.example.caveadventure.service.MapService;
import com.example.caveadventure.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PutMapping
    public JsonResult<Integer> move(Integer userid, Integer action){    // 需要对上下左右移动有编号，传入编号。如这里，上下左右，1234
        // 更改当前位置
        mapService.move(userid, action);

        // 返回当前位置的序号
        int[] now = mapService.findNowRoom(userid);
        return new JsonResult<Integer>(OK, 5*now[0]+now[1]);

        // 注意后期可能需要添加血量变化，变化后需要返回前端
    }



}
