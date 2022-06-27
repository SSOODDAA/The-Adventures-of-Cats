package com.example.caveadventure.dao;

import com.example.caveadventure.entity.MapEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MapReposityTests {
    @Autowired
    private MapReposity mapReposity;
    @Test
    public void insert()
    {
        List<Integer> list = Arrays.asList(1, 2, 3,4,5);
        MapEntity mapEntity=new MapEntity();
        mapEntity.setUserid(2);
        mapEntity.setNowroomx(0);
        mapEntity.setNowroomy(1);
        mapEntity.setRoute(list);
        mapEntity.setMagicroom(1);
        mapEntity.setDeadroom(list);
        MapEntity result=mapReposity.insert(mapEntity);
        if(result!=null)
        {
            System.out.println(result.toString());
        }
        else{
            System.out.println("插入失败");
        }
    }

    @Test
    public void find()
    {
        MapEntity result=mapReposity.findByUserid(1);
        if(result!=null)
        {
            System.out.println(result.toString());
        }
        else{
            System.out.println("查找失败");
        }
    }

    @Test
    public void update()
    {
        List<Integer> list = Arrays.asList(1, 2, 3,4,5);
        MapEntity mapEntity=new MapEntity();
        mapEntity.setUserid(1);
        mapEntity.setNowroomx(2);
        mapEntity.setNowroomy(1);
        mapEntity.setRoute(list);
        mapEntity.setMagicroom(1);
        mapEntity.setDeadroom(list);
        MapEntity result=mapReposity.save(mapEntity);
        if(result!=null)
        {
            System.out.println(result.toString());
        }
        else{
            System.out.println("更新失败！");
        }
    }

    @Test
    public void delete()
    {
        int flag;
        flag=mapReposity.deleteByUserid(2);
        System.out.println(flag);
    }
}
