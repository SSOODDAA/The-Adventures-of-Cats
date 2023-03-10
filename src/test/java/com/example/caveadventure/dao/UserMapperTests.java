package com.example.caveadventure.dao;

import com.example.caveadventure.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/*@SpringBootTest:标注表示当前的类是一个测试类*/
@SpringBootTest
/*@RunWith:启动这个单元测试类，需要传递一个参数——必须是SpringRunner的实例类型*/
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;
    /**
     * 单元测试方法：独立运行，做单元测试，提升代码的测试效率
     * 1.必须被@Test注解修饰
     * 2.返回值类型必须是void
     * 3.方法的参数列表不指定任何类型
     * 4.方法的访问修饰符必须是public
     */
    @Test
    public void insert()
    {
        UserEntity user=new UserEntity();
        user.setUsername("Lucy");
        user.setPwd("123");
        user.setBest(0);
        Integer rows=userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void findByUsername()
    {
        UserEntity user=userMapper.findByUsername("Judy");
        System.out.println(user);
    }

    @Test
    public void updateUsernameByUserid()
    {
        Integer rows=userMapper.updateUsernameByUserid(5,"Sally");
        System.out.println(rows);
    }
    @Test
    public void updatePasswordByUserid()
    {
        Integer rows=userMapper.updatePasswordByUserid(1,"zxc");
        System.out.println(rows);
    }

    @Test
    public void findByUserid()
    {
        UserEntity user=userMapper.findByUserid(1);
        System.out.println("最高分为"+user.getBest());
    }
}
