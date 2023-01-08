package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
// @RequiredArgsConstructor // final이 붙은 값으로 생성자를 만들어줌
public class OrderServiceImpl implements OrderService{

    // 인터페이스에만 의존함(DIP를 지킴) -> 뭐가 들어올지 모르고 logic만 실행하면 됨
    // 필드 주입법 - 외부에서 변경불가능해 순수한 자바코드로 테스트하기 힘들다는 단점이 있음 -> 권장하지 않음
    // @Autowired
    private final MemberRepository memberRepository;

    // @Autowired
    private final DiscountPolicy discountPolicy;

    // setter 주입법 - 선택, 변경가능성이 있는 상태일때 사용하는 방법
    /*
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("memberRepository = "+memberRepository);
        this.memberRepository = memberRepository;
    }
    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("discountPolicy = "+discountPolicy);
        this.discountPolicy = discountPolicy;
    }
    */

    // 생성자 주입법
    // 오직 생성자 주입법만 final 키워드 사용 가능
    @Autowired // 생성자가 하나뿐이면 생략 가능
    public OrderServiceImpl(MemberRepository memberRepository, /*@Qualifier("mainDiscountPolicy")*/ @MainDiscountPolicy DiscountPolicy discountPolicy) {
        System.out.println("1. orderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
     }

    /*
    @Autowired 일반 메서드 주입 -> 한 번에 여러 필드를 주입 받음 그러나 일반적으로 사용하지 않음
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
     */
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // FixDiscountPolicy를 RateDiscountPolicy로 변경하는 순간
    // OrderServiceImpl의 소스코드도 변경해야 함 -> OCP위반
    // private DiscountPolicy discountPolicy; 인터페이스에만 의존하게 됨 -> 구현체가 없는데 어떻게?
    // 이 문제를 해결하려면 누군가가 클라이언트인 OrderServiceImpl에
    // DiscountPolicy의 구현 객체를 대신 생성하고 주입해주어야 함.
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
