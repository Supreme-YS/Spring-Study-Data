package com.supreme.oembed.controller;

import com.supreme.oembed.service.OembedService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OembedController {

    private final OembedService oembedService;

    @GetMapping("/api/oembed")
    @ResponseBody
    public HttpEntity<Map<String, Object>> urlEmbed(@RequestParam(value = "url") String url) {
        return oembedService.embedProcess(url);
    }
}
