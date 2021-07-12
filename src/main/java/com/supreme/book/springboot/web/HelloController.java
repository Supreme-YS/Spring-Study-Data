package com.supreme.book.springboot.web;

import com.supreme.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/hello/dto")
    // @RequestParam : 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
