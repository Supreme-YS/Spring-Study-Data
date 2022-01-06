package com.ireland.ager.api.account;

import java.util.HashMap;

public interface AuthService {

    String getKakaoLoginUrl();
    HashMap<String, String> getKakaoTokens(String code);
    KakaoAccountRes getKakaoAccountInfo(String accessToken);

}
