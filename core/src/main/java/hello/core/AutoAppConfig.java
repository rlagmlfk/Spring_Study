package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// 기존 예제 코드를 남기기 위함
@ComponentScan(
        // basePackages = "hello.core.member", // 등록된 패키지 라이브러리만 찾는다.
        // basePackageClasses = AutoAppConfig.class,
        // 지정하지 않으면? hello.core부터 시작하여 스캔한다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
