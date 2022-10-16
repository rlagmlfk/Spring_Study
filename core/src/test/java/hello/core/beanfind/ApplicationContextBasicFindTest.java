package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        // System.out.println("memberService = "+memberService);
        // System.out.println("memberService.getClass() = "+memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
    @Test
    @DisplayName("이름없이 타입으로 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
    @Test
    @DisplayName("구체타입으로 조회") // 역할에 의존해야 하기 때문에 이렇게하면 좋지 않음 -> 유연성이 떨어짐
    void findBeanByName2(){
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 실패 테스트
    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX(){
        // ac.getBean("XXXXX", MemberService.class);
        // MemberService xxxxx = ac.getBean("XXXXX", MemberService.class);
        // 오른쪽의 예외가 터지면 성공, 안터지면 실패
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("XXXXX", MemberService.class));
    }
}
