package practice.spring.core.discount;

import practice.spring.core.member.Member;

public interface DiscountPolicy {

    int discount(Member member, int price);
}
