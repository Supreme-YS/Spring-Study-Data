package practice.spring.core.order;

import practice.spring.core.discount.DiscountPolicy;
import practice.spring.core.discount.FixDiscountPolicy;
import practice.spring.core.discount.RateDiscountPolicy;
import practice.spring.core.member.Member;
import practice.spring.core.member.MemberRepository;
import practice.spring.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //TODO  : OrderServiceImpl은 현재 DiscountPolicy라는 추상(인터페이스) 뿐만 아니라 RateDiscountPolicy에도 의존하고 있다.
    //FIXME : OCP를 위반하고 있으므로, 인터페이스에만 의존하도록 의존 관계를 변경하면 된다!

    // 정액 할인 정책
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 정률 할인 정책
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 인터페이스에만 의존한다. 어떤 구현 객체가 들어올지는 알 수 없음.
    // 외부 -> AppConfig에서 결정해서 보낸다.
    // 여기에선 MemoryMemberRepository, FixDiscountPolicy가 AppConfig에서 결정해서 보내는 상황
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
