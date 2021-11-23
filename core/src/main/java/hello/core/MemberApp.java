package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;


/**
 * 순수 자바로 테스트 코드 및 구현 코드 구현
 */
public class MemberApp {

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        //MemberServiceImpl을 만들고 내가 만든 MemberServiceImpl은
        //MemoryMemberRepository를 만들어서 사용할거다. 라는 값을 memberService에 주입!
//        MemberService memberService = new MemberServiceImpl();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
