package com.supreme.oembed.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@Slf4j
public class ProviderResponseService {
    @Value("${provider.youtube.url}")
    private String YOUTUBE;

    @Value("${provider.twitter.url}")
    private String TWITTER;

    @Value("${provider.vimeo.url}")
    private String VIMEO;

    @Value("${provider.instagram.url}")
    private String INSTAGRAM;


    /**
     * @param paramUrl
     * @return
     * @Method : getYoutubeObject
     * @Description : paramUrl이 youtube링크일 때 oembed 결과를 반환한다.
     */
    public ResponseEntity<Map<String, Object>> getYoutubeObject(String paramUrl) {
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
        result.put("response", embedResult);
        System.out.println(result);
        return new ResponseEntity<>(embedResult, HttpStatus.OK);
    }

    /**
     * @param paramUrl
     * @return
     * @Method : getTwitterObject
     * @Description : paramUrl이 twitter링크일 때 oembed 결과를 반환한다.
     */
    public ResponseEntity<Map<String, Object>> getTwitterObject(String paramUrl) {
        Map<String, Object> embedResult;
        Map<String, Object> result = new HashMap<>();

        boolean isTwitterPost = Pattern.compile("(https://twitter.com/.*/status/.*?)").matcher(paramUrl).find();
        if (isTwitterPost == false) {
            result.put("result", "Fail");
            result.put("response", "지원하지 않는 형식의 Twitter URL 입니다.");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        RestTemplate template = new RestTemplate();
        embedResult = template.getForObject(TWITTER + paramUrl, Map.class);

        result.put("result", "success");
        result.put("response", embedResult);
        System.out.println(result);
        return new ResponseEntity<>(embedResult, HttpStatus.OK);
    }

    /**
     * @param paramUrl
     * @return
     * @Method : getYoutubeObject
     * @Description : paramUrl이 vimeo링크일 때 oembed 결과를 반환한다.
     */
    public ResponseEntity<Map<String, Object>> getVimeoObject(String paramUrl) {
        Map<String, Object> embedResult;
        Map<String, Object> result = new HashMap<>();

        boolean isVimeoPost = Pattern.compile("(https://vimeo.com/.*?)").matcher(paramUrl).find();
        if (isVimeoPost == false) {
            result.put("result", "Fail");
            result.put("response", "지원하지 않는 형식의 Vimeo URL 입니다.");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        RestTemplate template = new RestTemplate();
        embedResult = template.getForObject(VIMEO + paramUrl, Map.class);

        result.put("result", "success");
        result.put("response", embedResult);
        System.out.println(result);
        return new ResponseEntity<>(embedResult, HttpStatus.OK);
    }
}
