package practice.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import practice.spring.core.member.Grade;
import practice.spring.core.member.Member;
import practice.spring.core.member.MemberService;

/**
 * ApplicationContext를 스프링 컨테이너라고 한다.
 * AppConfig에서 @Bean이라 적힌 메서드를 모두 호출해서 스프링 컨테이너에 등록한다.
 * 이렇게 등록된 객체를 스프링 빈이라고 한다.
 * @Bean이 붙은 메서드의 명을 스프링 빈의 이름으로 사용한다.
 */
public class MemberApp {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
