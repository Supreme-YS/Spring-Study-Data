package com.supreme.board.controller;

import com.supreme.board.domain.entity.Member;
import com.supreme.board.domain.entity.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class MemberController {

    private MemberRepository member;

    public MemberController(MemberRepository member) {
        this.member = member;
    }

    @RequestMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("signIn")
    public String signIn(String inputEmail, String inputPassword) {
        log.info("id : {} , pw : {}", inputEmail, inputPassword);
        Member member = this.member.findMember(inputEmail, inputPassword);
        if(member != null) {
            return "loginOK";
        } else {
            return "loginFail";
        }
    }
}
