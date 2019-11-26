package org.jeecg.modules.demo.czl.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/czl/demo")
public class HelloController {

    @Autowired
    RestTemplate restTemplate;
    //消费者。
    @GetMapping(value = "/hi")
    /*HystrixCommand注解对该方法创建了熔断器的功能，并指定了fallbackMethod熔断方法，
    熔断方法直接返回了一个字符串，字符串为”hi,”+name+”,sorry,error!”
    */
    @HystrixCommand(fallbackMethod = "hiError")
    public String hi(@RequestParam String name) {
        return  restTemplate.getForObject("http://SERVICE-HI/hi?name=" + name, String.class);
    }
    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }
    @GetMapping(value = "/hellos")
    public String hi2() {
        //http://jeecg-boot:8080/jeecg-boot/czl/demo/hello
        return  restTemplate.getForObject("http://JEECG-BOOT/jeecg-boot/czl/demo/hello", String.class);
    }
}
