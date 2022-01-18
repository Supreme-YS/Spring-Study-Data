package com.supreme.oembed.service;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.parser.ParseException;

@Service
public class OembedService {

    /**
     * @Method : getUrlFromProviderJson
     * @Description : provider.json에서 url 목록을 가져온다.
     * @throws IOException
     * @throws ParseException
     */
    public static void getUrlFromProviderJson() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        // JSON 파일 읽기
        Reader reader = new FileReader("../resources/provider/provider.json");
        JSONObject jsonObject = (JSONObject) parser.parse(reader);

        String providerName = (String) jsonObject.get("provider_name");
        String url = (String) jsonObject.get("url");

        System.out.println("url = " + url);
        System.out.println("providerName = " + providerName);
    }

    /**
     * @Method : embedProcess
     * @Description : 사용자가 입력한 url에서 domain 값을 추출하여 분기한다.
     * @param paramUrl
     * @return
     */
    public HttpEntity<Map<String, Object>> embedProcess(String paramUrl) {
        Map<String, Object> result = new HashMap<>();
        String domain = "";

        try {
            // 유저가 입력한 도메인을 확인
            domain = getDomainName(paramUrl);
        } catch (URISyntaxException e) {
            result.put("result", "Fail");
            result.put("response", "잘못된 URL 입니다.");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        //TODO Domain별 처리 필요
    }

}
