package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Version 1.0
 * @Description HelloController
 **/
@RestController
public class HelloController {

    /**
     * @Description: hello 测试的路由api方法
     * @param
     * @return Object
     */
    @GetMapping("hello")
    public Object hello() {
        return "Hello Java~";
    }

}
