package com.example.caveadventure;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//MapperScan注解指定当前项目中的Mapper接口路径的位置，在项目启动的时候会自动加载所有的接口
@MapperScan("com.example.caveadventure.dao")
public class CaveAdventureApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaveAdventureApplication.class, args);
    }

}
