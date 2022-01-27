package practice.spring.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import practice.spring.core.member.Grade;
import practice.spring.core.member.Member;
import practice.spring.core.member.MemberService;
import practice.spring.core.member.MemberServiceImpl;


class OrderServiceImplTest {

    private final MemberService memberService = new MemberServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        //given
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
        //when
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}