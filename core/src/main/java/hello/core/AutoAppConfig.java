package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
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

    // 이 경우 수동 빈 등록이 우선권을 가진다
    // 수동 빈이 자동 빈을 오버라이딩 해버림
    // 하지만 버그가 발생할 수 있기에
    // 최근의 스프링에서는 에러메시지를 띄움(부트로 실행시)
    // application.properties에서 설정 가능
    /*
    @Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
     */

}
