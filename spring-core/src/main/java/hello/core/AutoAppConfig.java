package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// @ComponentScan을 사용하면 @Component가 붙은 것도 다 등록한다.
@ComponentScan(
        // AppConfig.class를 제외시킴..
        // 예제의 유지를 위함
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // BeanConflictException error용 코드!
    //@Bean(name = "memoryMemberRepository") // 첫 글자가 소문자가 되니까!
    //MemberRepository memberRepository() {
    //    return new MemoryMemberRepository();
    //}

    /*
    * The bean 'memoryMemberRepository', defined in class path resource [hello/core/AutoAppConfig.class], could not be registered. A bean with that name has already been defined in file [/Users/supreme-ys/study/spring/spring-inflearn/core/out/production/classes/hello/core/member/MemoryMemberRepository.class] and overriding is disabled.
      Action:
      Consider renaming one of the beans or enabling overriding by setting spring.main.allow-bean-definition-overriding=true
    */

}
