package com.supreme.oembed.service;


import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@Slf4j
public class ProviderResponseService {
    private static String YOUTUBE = "https://www.youtube.com/oembed?url=";
    private static String TWITTER = "https://publish.twitter.com/oembed?url=";
    private static String VIMEO = "https://vimeo.com/api/oembed.json?url=";

    private static ProviderService providerService;

    /**
     * @Method : getUrlFromProviderJson
     * @Description : provider.json에서 url 목록을 가져온다.
     * @throws IOException
     * @throws ParseException
     */
    public static String getUrlFromProviderJson(String provider) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        // JSON 파일 읽기
        Reader reader = new FileReader("provider/provider.json");
        JSONObject jsonObject = (JSONObject) parser.parse(reader);

        JSONArray providerArr = (JSONArray) jsonObject.get("provider_name");
        String url = "";

        for (int i=0; i < providerArr.size(); i++) {
            if (providerArr.get(i).equals(provider)) {
                url = (String) jsonObject.get("url");
            }
        }
        System.out.println("url = " + url);

        return url;
    }

    public HttpEntity<Map<String, Object>> getYoutubeHtml(String paramUrl) {
        Map<String, Object> embedResult;
        Map<String, Object> result = new HashMap<>();

        boolean isYoutubePost = Pattern.compile("(https://www.youtube.com/watch.*?)").matcher(paramUrl).find();
        if (isYoutubePost == false) {
            result.put("result", "Fail");
            result.put("response", "지원하지 않는 형식의 Youtube URL 입니다.");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        RestTemplate template = new RestTemplate();
        embedResult = template.getForObject(YOUTUBE + paramUrl, Map.class);

        result.put("result", "success");
        result.put("response", embedResult.get("html"));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public HttpEntity<Map<String, Object>> getTwitterHTML(String paramUrl) {
        Map<String, Object> embedResult;
        Map<String, Object> result = new HashMap<>();

        boolean isTwitterPost = Pattern.compile("(https://twitter.com/.*/status/.*?)").matcher(paramUrl).find();
        if (isTwitterPost == false) {
            result.put("result", "Fail");
            result.put("response", "지원하지 않는 형식의 Twitter URL 입니다.");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        // API 요청
        RestTemplate template = new RestTemplate();
        embedResult = template.getForObject(TWITTER + paramUrl, Map.class);

        result.put("result", "success");
        result.put("response", embedResult.get("html"));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public HttpEntity<Map<String, Object>> getVimeoHTML(String paramUrl) {
        Map<String, Object> embedResult;
        Map<String, Object> result = new HashMap<>();

        boolean isVimeoPost = Pattern.compile("(https://vimeo.com/.*?)").matcher(paramUrl).find();
        if (isVimeoPost == false) {
            result.put("result", "Fail");
            result.put("response", "지원하지 않는 형식의 Vimeo URL 입니다.");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        // API 요청
        RestTemplate template = new RestTemplate();
        embedResult = template.getForObject(VIMEO + paramUrl, Map.class);
        System.out.println(embedResult);
        result.put("result", "success");
        result.put("response", embedResult.get("html"));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
