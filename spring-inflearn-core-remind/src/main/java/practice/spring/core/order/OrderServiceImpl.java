package practice.spring.core.order;

import practice.spring.core.discount.DiscountPolicy;
import practice.spring.core.discount.FixDiscountPolicy;
import practice.spring.core.discount.RateDiscountPolicy;
import practice.spring.core.member.Member;
import practice.spring.core.member.MemberRepository;
import practice.spring.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 정액 할인 정책
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 정률 할인 정책
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
