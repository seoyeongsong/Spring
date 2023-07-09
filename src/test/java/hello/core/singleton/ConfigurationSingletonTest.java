package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    @DisplayName("MemberRepository의 싱글톤 패턴 확인하기")
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        
        // 메소드를 호출하기 위해 구체화 타입으로 빈을 조회, 지양할 것.
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository1 ::" + memberRepository1);
        System.out.println("orderService -> memberRepository2 :: " + memberRepository2);
        System.out.println("memberRepository :: " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }


    @Test
    @DisplayName("configuration annotation test")
    void configurationDeep() {

        // AppConfig 클래스를 매개변수로 사용하면, AppConfig 자체도 Bean으로 등록되기 때문에
        // 클래스를 getBean으로 찾아 조회할 수 있다.
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean.getClass = " + bean.getClass());
        System.out.println("bean = " + bean);
    }
}
