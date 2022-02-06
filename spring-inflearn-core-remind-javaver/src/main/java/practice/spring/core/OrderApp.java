package practice.spring.core;

import practice.spring.core.member.Grade;
import practice.spring.core.member.Member;
import practice.spring.core.member.MemberService;
import practice.spring.core.order.Order;
import practice.spring.core.order.OrderService;

public class OrderApp {

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
    }
}
