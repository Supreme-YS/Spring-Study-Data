package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

// command + shift + t 테스트 도구 자동 빌드
@Component // @ComponentScan을 통한 Bean 등록
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10; // 10% 할인
    @Override
    public int discount(Member member, int price) {

        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
