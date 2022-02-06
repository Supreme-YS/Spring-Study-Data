package practice.spring.core;

import practice.spring.core.discount.FixDiscountPolicy;
import practice.spring.core.member.MemberService;
import practice.spring.core.member.MemberServiceImpl;
import practice.spring.core.member.MemoryMemberRepository;
import practice.spring.core.order.OrderService;
import practice.spring.core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
                new MemoryMemberRepository(),
                new FixDiscountPolicy());
    }
}
