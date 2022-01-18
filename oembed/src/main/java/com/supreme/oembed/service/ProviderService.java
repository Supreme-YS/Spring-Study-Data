package com.supreme.oembed.service;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Service
@RequiredArgsConstructor
public class ProviderService {

    private final String YOUTUBE;
    {
        try {
            YOUTUBE = getUrlFromProviderJson("YouTube");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private final String TWITTER;

    {
        try {
            TWITTER = getUrlFromProviderJson("Twitter");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private final String VIMEO;

    {
        try {
            VIMEO = getUrlFromProviderJson("Vimeo");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Method : getUrlFromProviderJson
     * @Description : provider.json에서 url 목록을 가져온다.
     * @throws IOException
     * @throws ParseException
     */
    public static String getUrlFromProviderJson(String provider) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        // JSON 파일 읽기
        Reader reader = new FileReader("../resources/provider/provider.json");
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
}
