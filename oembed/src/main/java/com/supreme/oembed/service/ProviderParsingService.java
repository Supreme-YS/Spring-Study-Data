package com.supreme.oembed.service;

import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Getter
@Service
public class ProviderParsingService {
    /**
     * @throws IOException
     * @throws ParseException
     * @Method : getUrlFromProviderJson
     * @Description : provider.json에서 url 목록을 가져온다.
     */
    //FIXME : json 파일을 파싱해올 수 있게 수정이 필요하다.
    public static String getUrlFromProviderJson(String provider) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        // JSON 파일 읽기
        Reader reader = new FileReader("provider/provider.json");
        JSONObject jsonObject = (JSONObject) parser.parse(reader);

        JSONArray providerArr = (JSONArray) jsonObject.get("provider_name");

        String url = "";
        for (int i = 0; i < providerArr.size(); i++) {
            if (providerArr.get(i).equals(provider)) {
                url = (String) jsonObject.get("url");
            }
        }
        System.out.println("url = " + url);

        return url;
    }
}
