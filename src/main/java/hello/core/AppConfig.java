package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // MemberService는 MemoryMemberRepository를 사용하므로 할당해준다.
    /*생성자를 통한 객체 주입*/

    // refactoring : 각 역할이 드러나도록 메소드 추출
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    private static MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(discountPolicy(), memberRepository());
   }

    @Bean
   // 새로운 할인 정책 적용 시 AppConfig만 수정하므로 사용영역은 수정사항이 없다.
   public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
       return new RateDiscountPolicy();
   }
}
