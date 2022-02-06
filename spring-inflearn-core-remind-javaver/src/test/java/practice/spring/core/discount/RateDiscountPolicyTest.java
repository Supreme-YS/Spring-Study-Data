package practice.spring.core.discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import practice.spring.core.member.Grade;
import practice.spring.core.member.Member;


class RateDiscountPolicyTest {

    DiscountPolicy discountPolicy = new RateDiscountPolicy();
    @Test
    @DisplayName("VIP는 10%의 할인이 적용되어야 한다")
    void vipDiscount() {
        //given
        Member member = new Member(1L, "memberV", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("BASIC은 할인이 적용되면 안된다.")
    void basicDiscount() {
        //given
        Member member = new Member(2L, "memberB", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        Assertions.assertThat(discount).isEqualTo(0);
    }
}