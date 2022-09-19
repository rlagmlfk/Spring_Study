package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;
/*  setter 주입법 - 단점: 누군가가 MemberController를 호출했을 때 public으로 열려있어야함
    바꿀 이유가 없는데 public으로 노출됨 문제가 생길 수 있음   
    private MemberService memberService;
    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
    */

    // @Autowired private MemberService memberService; 필드주입 - 별로 안좋음
    // 스프링 컨테이너에서 MemberService를 가져온다
    // 요즘은 생성자 주입법을 사용
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
