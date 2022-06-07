package com.example.caveadventure.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${name}")
    private String name;

    @RequestMapping("/hello")
    public String hello(){
        return "Hello World!" + name + "咱们来试试中文！！";
    }

}
