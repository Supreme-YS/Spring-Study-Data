package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 아래 두 줄의 코드는 현재 DIP를 위반한 코드이다. 추상과 구현 둘 다 의존하고 있기 때문.
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 오로지 인터페이스에만 의존하게끔 설정해준다.
    // 그런데 구현체가 없는데 어떻게 실행함..? 안됨 --> Null Pointer Exception이 터짐
    // 따라서, 어느 누군가가 DiscountPolicy의 구현 객체를 대신 생성하고 주입해주어야 함.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 생성자 주입
    @Autowired // 생성자가 딱 1개 이므로 생략이 가능하다.
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 새로 생성된 주문 객체를 만들어서 반환!
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 싱글톤 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
