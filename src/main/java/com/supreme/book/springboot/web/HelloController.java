package com.supreme.book.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // 최근 방법
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    // 예전 방법
    @RequestMapping(value = "/hello2",method = RequestMethod.GET)
    public String hello2() { return "hello2";}
}
