package com.example.caveadventure.dao;

import com.example.caveadventure.entity.ProductEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductMapperTests {
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void findById()
    {
        ProductEntity productEntity=productMapper.findById(1);
        System.out.println(productEntity);
    }
}
