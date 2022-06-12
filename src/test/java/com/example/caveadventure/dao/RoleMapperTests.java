package com.example.caveadventure.dao;

import com.example.caveadventure.entity.RoleEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.management.relation.Role;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RoleMapperTests {
    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void findByRoleid()
    {
        RoleEntity role=roleMapper.findByRoleid(1);
        System.out.println(role);
    }
}
