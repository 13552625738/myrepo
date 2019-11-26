package org.jeecg.modules.demo.test.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.jeecg.modules.demo.czl.entity.Demo;
@RunWith(SpringRunner.class)
@SpringBootTest
public class tesss{

    @Autowired
    private RestTemplate restTemplate;
    @Test
    public void demo(){

        System.out.println(restTemplate.getForObject("http://localhost:8080/jeecg-boot/czl/demo/list",null,Demo.class).toString());
    }
}
