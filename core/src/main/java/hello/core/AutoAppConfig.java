package hello.core;

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

}
