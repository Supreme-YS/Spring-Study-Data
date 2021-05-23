package spring.springstudy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.springstudy.repository.MemberRepository;
import spring.springstudy.repository.MemoryMemberRepository;
import spring.springstudy.service.MemberService;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository(); // 인터페이스는 new가 안됨; 구현체로 new 생성
    }
}
