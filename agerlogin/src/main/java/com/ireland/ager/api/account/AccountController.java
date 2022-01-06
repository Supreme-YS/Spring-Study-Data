package com.ireland.ager.api.account;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @FileName : AccountController
 * @Class 설명 : OAuth, account 관련 인증을 매핑하는 REST Controller
 */
@Api(value = "account 및 인증 API", tags = {"accountController"}, description = "유저 관련 API를 매핑하는 컨트롤러")
@RestController
@RequestMapping("/api/auth")
public class AccountController {

    private final String EMAIL_SUFFIX = "@gmail.com";

    @Autowired
    AuthService authService;

    @Autowired
    AccountService accountService;
    // localhost:8080/api/auth/kakao/showlogin
    //
    @GetMapping("/kakao/showlogin")
    @ApiOperation(value = "카카오 로그인을 위한 요청 URL 전송", notes = "카카오 로그인을 위한 요청 URL을 전송한다. 해당 URL로 GET 요청을 보내면 된다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "정상적인 URL 전송 완료"),
    })
    public ResponseEntity<String> showLogin() {
        /**
         * @Method Name : showLogin
         * @Method 설명 : 카카오 로그인을 위한 요청 URL을 전송하는 Method, 해당 URL로 GET 요청을 전송 시 카카오톡 로그인 페이지로 이동된다.
         */
        return new ResponseEntity<>(authService.getKakaoLoginUrl(), HttpStatus.OK);
    }

    @GetMapping("/kakao/login")
    @ApiOperation(value = "kakao token을 얻어와 유저정보 조회 후, 회원가입 혹은 로그인 수행 후 정보 반환", notes = "Token은 매 실행 시 갱신된다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "정상적으로 account의 JSON이 반환되었음"),
    })
    public ResponseEntity<AccountRes> getTokenAndJoinOrLogin(@ApiParam(value="Token 생성에 사용될 Code", required = true) @RequestParam("code") String code) {
        /**
         * @Method Name : getTokenAndJoinOrLogin
         * @Method 설명 : 카카오톡 로그인 이후 Token을 얻어온 후, 회원가입 또는 로그인을 처리하는 Method
         */

        // 1. Token을 발급받는다.
        HashMap<String, String> kakaoTokens = authService.getKakaoTokens(code);

        // 2. Token 값을 통해 accountInfo를 받아온다.
        KakaoAccountRes kakaoAccountRes = authService.getKakaoAccountInfo(kakaoTokens.get("access_token"));

        // 3. accountInfo의 내용이 회원 DB에 존재하는가?
        String accountEmailOrId = kakaoAccountRes.getKakao_account().getEmail();
        if(accountEmailOrId == null || accountEmailOrId == "") {
            accountEmailOrId = String.valueOf(kakaoAccountRes.getId()) + EMAIL_SUFFIX;
        }

        AccountRes accountResForCheck = accountService.findaccountByAccountEmail(accountEmailOrId);

        AccountRes accountRes;
        if(accountResForCheck != null) {
            // 존재한다면 Token 값을 갱신하고 반환한다.
            accountRes = authService.refreshTokensForExistaccount(accountResForCheck.getAccountEmail(), kakaoTokens.get("access_token"), kakaoTokens.get("refresh_token"));
        } else {
            // 존재하지 않는다면 회원 가입 시키고 반환한다.
            accountRes = accountService.insertaccount(kakaoAccountRes.toaccount(kakaoTokens.get("access_token"), kakaoTokens.get("refresh_token")));
        }

        return new ResponseEntity<>(accountRes, HttpStatus.OK);
    }

}
