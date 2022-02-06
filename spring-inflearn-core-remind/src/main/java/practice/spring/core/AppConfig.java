package practice.spring.core;

import practice.spring.core.discount.DiscountPolicy;
import practice.spring.core.discount.FixDiscountPolicy;
import practice.spring.core.discount.RateDiscountPolicy;
import practice.spring.core.member.MemberRepository;
import practice.spring.core.member.MemberService;
import practice.spring.core.member.MemberServiceImpl;
import practice.spring.core.member.MemoryMemberRepository;
import practice.spring.core.order.OrderService;
import practice.spring.core.order.OrderServiceImpl;

/**
 * 구현 객체를 생성하고, 연결하는 책임을 담당하는 AppConfig
 */
public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }

    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
        // 만약에 정액할인정책이 아니라 정률로만 바꾸고 싶다면?
        // return new FixDiscountPolicy();
        // 이런식으로 "구성 영역"에서 바꿔만 주면 된다.
        return new RateDiscountPolicy();
    }
}
