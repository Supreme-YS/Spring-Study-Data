package practice.spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import practice.spring.core.discount.DiscountPolicy;
import practice.spring.core.discount.RateDiscountPolicy;
import practice.spring.core.member.MemberRepository;
import practice.spring.core.member.MemberService;
import practice.spring.core.member.MemberServiceImpl;
import practice.spring.core.member.MemoryMemberRepository;
import practice.spring.core.order.OrderService;
import practice.spring.core.order.OrderServiceImpl;

/**
 * @Configuration : 설정을 구성한다는 뜻의 어노테이션
 * @Bean : 스프링 컨테이너에 빈(bean)으로 등록한다.
 */
@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // 만약에 정액할인정책이 아니라 정률로만 바꾸고 싶다면?
        // return new FixDiscountPolicy();
        // 이런식으로 "구성 영역"에서 바꿔만 주면 된다.
        return new RateDiscountPolicy();
    }
}
