package com.supreme.oembed.controller;

import com.supreme.oembed.service.OembedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OembedController {

    @Autowired
    OembedService oembedService;

    @GetMapping("/api/oembed")
    public HttpEntity<Map<String, Object>> urlEmbed(@RequestParam(value = "url") String url) {
        return oembedService.embedProcess(url);
    }
}
