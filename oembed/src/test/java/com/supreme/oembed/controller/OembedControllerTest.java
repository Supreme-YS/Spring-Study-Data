package com.supreme.oembed.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OembedControllerTest {

    @Autowired
    private TestRestTemplate template;

    private final String testUrl = "https://www.youtube.com/watch?v=dBD54EZIrZo";

    @Test
    public void embedProcessTest() {
        ResponseEntity<Map> response = template.getForEntity("/api/oembed?url=" + testUrl, Map.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody().get("result")).isEqualTo("success");
    }
}