package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * 순수 자바로 테스트 코드 및 구현 코드 구현
 */
public class MemberApp {

    public static void main(String[] args) {

        // 1. 기존 버전
        //MemberServiceImpl을 만들고 내가 만든 MemberServiceImpl은
        //MemoryMemberRepository를 만들어서 사용할거다. 라는 값을 memberService에 주입!
        //MemberService memberService = new MemberServiceImpl();

        // 2. 의존성 주입 버전
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();

        // 3. 스프링 사용한 버전
        // AppConfig에 있는 환경정보를 기반으로 스프링 컨테이너에 넣고 관리를 해줌
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);// (메서드 이름, 타입)

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
