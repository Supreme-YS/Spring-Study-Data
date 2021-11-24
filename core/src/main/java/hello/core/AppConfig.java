package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// command + option + m = 메서드 추출
// 리팩토링하여 역할을 좀 더 명확하게 만듦.
// MemoryMemberRepository의 중복을 제거함

// 스프링으로 전환 시작! @Configuration, @Bean
// @Bean 을 붙이면 스프링 컨테이너에 등록이 된다!
@Configuration
public class AppConfig {

    // 싱글톤
    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()
    //두 번이 호출 되었네? 싱글톤 깨진거 아니냐..? --> 테스트 코드로 실험해보자
    //MemberServiceImpl.class 파일의 디버그를 찍어보자.

    //예상 출력 순서 - 출력 순서는 보장하지 않지만, 종류와 수는 보장된다.
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //call AppConfig.memberRepository

    //실제 출력 순서 - 모두 한 번씩만 출력이 되었다.
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.orderService

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy(); // 정률 할인 정책으로 변경
    }
}
