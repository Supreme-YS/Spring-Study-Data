package spring.springstudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import spring.springstudy.service.MemberService;

@Controller
public class MemberController {
    private final MemberService memberService;

    // Dependency Injection : 의존 관계를 주입시켜 줌.
    // MemberController가 생성될 때 Spring bin에 등록되어있는 MemberService 객체를 가져다가 넣어준다.
    @Autowired // Spring 컨테이너에서 MemberService 클래스를 연결, 하지만 순수한 자바 클래스이기 때문에 @Service를 해당 클래스에 적어준다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
