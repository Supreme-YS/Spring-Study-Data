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
    //TODO  : OrderServiceImpl은 현재 DiscountPolicy라는 추상(인터페이스) 뿐만 아니라 RateDiscountPolicy에도 의존하고 있다.
    //FIXME : OCP를 위반하고 있으므로, 인터페이스에만 의존하도록 의존 관계를 변경하면 된다!
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
