package com.ireland.ager.api.account;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class AuthServiceImpl implements AuthService {

    private final String KAKAO_URL = "https://kauth.kakao.com";
    private final String TOKEN_TYPE = "Bearer";

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoRestApiKey;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String kakaoRedirectUrl;

    @Value("${spring.security.oauth2.client.provider.kakao.account-info-uri}")
    private String kakaoaccountInfoUrl;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoRestSecretKey;

    @Override
    public String getKakaoLoginUrl() {
        /**
         * @Method Name : getKakaoLoginUrl
         * @Method 설명 : 카카오 로그인을 위한 요청 URL을 반환하는 Method, 해당 URL로 GET 요청을 전송 시 카카오톡 로그인 페이지로 이동된다.
         */
        return new StringBuilder()
                .append(KAKAO_URL).append("/oauth/authorize?client_id=").append(kakaoRestApiKey)
                .append("&redirect_uri=").append(kakaoRedirectUrl)
                .append("&response_type=code")
                .toString();
    }

    @Override
    public HashMap<String, String> getKakaoTokens(String code) {
        /**
         * @Method Name : getKakaoTokens
         * @Method 설명 : 발급된 code를 통해 Access Token과 Refresh Token을 반환한다.
         */
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> httpBody = new LinkedMultiValueMap<>();
        httpBody.add("grant_type", "authorization_code");
        httpBody.add("client_id", kakaoRestApiKey);
        httpBody.add("redirect_uri", kakaoRedirectUrl);
        httpBody.add("code", code);
        httpBody.add("client_secret", kakaoRestSecretKey);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenReq = new HttpEntity<>(httpBody, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<HashMap> tokenResEntity = restTemplate.exchange(KAKAO_URL + "/oauth/token", HttpMethod.POST, kakaoTokenReq, HashMap.class);

        return tokenResEntity.getBody();
    }

    @Override
    public KakaoAccountRes getKakaoAccountInfo(String accessToken) {
        /**
         * @Method Name : getKakaoAccountInfo
         * @Method 설명 : 발급된 token을 통해 kakao account 정보를 반환한다.
         */
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        httpHeaders.add("Authorization", TOKEN_TYPE + " " + accessToken);

        HttpEntity<MultiValueMap<String, String>> kakaoAccountInfoReq = new HttpEntity<>(httpHeaders);
        ResponseEntity<KakaoAccountRes> accountInfo = restTemplate.exchange(kakaoaccountInfoUrl, HttpMethod.GET, kakaoAccountInfoReq, KakaoAccountRes.class);

        return accountInfo.getBody();
    }

}
