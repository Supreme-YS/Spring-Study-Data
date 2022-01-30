package com.kmong.backend.modules.account;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "/api")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;

    // 전체 회원 조회
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAccount() {
        List<Account> allAccount = accountService.findAllAccount();
        return new ResponseEntity<>(allAccount, HttpStatus.OK);
    }
    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody Account account) {
        Account saveAccount = accountService.signUp(account);
        return new ResponseEntity<>(saveAccount, HttpStatus.CREATED);
    }

    // 로그인
    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        UserDetails loginAccount = accountService.loadUserByUsername(account.getEmail());
        return new ResponseEntity<>(loginAccount, HttpStatus.OK);
    }

    // 로그아웃
    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}