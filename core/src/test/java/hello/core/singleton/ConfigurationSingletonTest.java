package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
    
    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = "+memberRepository1);
        System.out.println("orderService -> memberRepository = "+memberRepository2);
        System.out.println("memberRepository = "+memberRepository);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = "+bean.getClass());
        // ->  bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$5fc2e21d
        // 순수한 클래스라면 class hello.core.AppConfig 라고 출력되어야 함
        // 즉, 내가 만든 클래스가 아니라 스프링이 빈을 등록하는 과정에서 
        // CGLIB이라는 바이트 코드 조작 라이브러리를 사용해서 AppConfig클래스를 상속받은
        // 임의의 다른 클래스를 만들어 그 클래스를 스프링 빈으로 등록한 것
    }
}
