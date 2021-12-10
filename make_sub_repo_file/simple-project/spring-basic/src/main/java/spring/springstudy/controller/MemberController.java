package spring.springstudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring.springstudy.domain.Member;
import spring.springstudy.service.MemberService;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    // Dependency Injection : 의존 관계를 주입시켜 줌.
    // MemberController가 생성될 때 Spring bean에 등록되어있는 MemberService 객체를 가져다가 넣어준다.
    @Autowired // Spring 컨테이너에서 MemberService 클래스를 연결, 하지만 순수한 자바 클래스이기 때문에 @Service를 해당 클래스에 적어준다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        // Add value test code
        System.out.println("member : " + member.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers(); // cmd + option + v
        model.addAttribute("members", members);
        return "members/memberList";
    }
}

