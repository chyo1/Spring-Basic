package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    /*
            할인 정책이 변경되면, 클라이언트 코드인 OrderServiceImpl 코드의 변경이 필요함 -> DIP 위반
         */
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    /*
        그럼 인터페이스에만 의존하게 변경? -> NPE 발생
     */
//    private final DiscountPolicy discountPolicy;

    /*
        인터페이스에만 의존할 수 있게 생성자 주입으로 변경
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // for test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
