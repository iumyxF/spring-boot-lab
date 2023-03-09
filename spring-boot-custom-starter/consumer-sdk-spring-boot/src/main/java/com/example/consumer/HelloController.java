package com.example.consumer;

import com.example.sdk.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author feng
 * @date 2023/3/9 20:39
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    /**
     * HelloService在我们自定义的starter中已经完成了自动配置，所以此处可以直接注入
     */
    @Resource
    private HelloService helloService;

    @GetMapping("/say")
    public String sayHello() {
        return helloService.sayHello();
    }
}
