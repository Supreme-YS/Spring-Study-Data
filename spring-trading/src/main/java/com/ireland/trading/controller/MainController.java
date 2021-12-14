package com.ireland.trading.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MainController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("admin")
    public String admin() {
        return "admin";
    }

}
