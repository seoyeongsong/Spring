package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;


    // final 선언을 삭제하고, setter를 생성해준 뒤 @Autowired 어노테이션을 추가하여 주입할 수 있다.
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy){
//        System.out.println("2. setDiscountPolicy call");
//        this.discountPolicy = discountPolicy;
//    }

//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository){
//        System.out.println("3. setMemberRepository call");
//        this.memberRepository = memberRepository;
//    }

    // 스프링 빈을 등록할 때 객체를 생성하기 위해 생성자를 호출하기 때문에 함께 의존성 주입이 일어난다.
    public OrderServiceImpl(@MainDiscountPolicy DiscountPolicy discountPolicy, MemberRepository memberRepository) {
        System.out.println("1. discountPolicy = " + discountPolicy);
        System.out.println("1. memberRepository = " + memberRepository);
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도로 생성함
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
