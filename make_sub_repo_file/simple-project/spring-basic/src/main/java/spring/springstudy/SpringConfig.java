package spring.springstudy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.springstudy.repository.MemberRepository;
import spring.springstudy.service.MemberService;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
}
//@Configuration
//public class SpringConfig {
//
////    private DataSource dataSource;
////
////    @Autowired
////    public SpringConfig(DataSource dataSource) {
////        this.dataSource = dataSource;
////    }
////    private EntityManager em;
//    private final MemberRepository memberRepository;
//
//    @Autowired
//    public SpringConfig(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public SpringConfig(EntityManager em, MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//        this.em = em;
//    }
//
//    @Bean
//    public MemberService memberService() {
//        return new MemberService(memberRepository);
//    }
//
//    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository(); // 인터페이스는 new가 안됨; 구현체로 new 생성
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);
////        return new JpaMemberRepository(em);
//    }
//}
