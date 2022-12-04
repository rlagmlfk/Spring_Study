package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{
    // 인터페이스에만 의존함(DIP를 지킴) -> 뭐가 들어올지 모르고 logic만 실행하면 됨
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
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
