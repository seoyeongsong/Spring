package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {
    @Test
    @DisplayName("스프링이 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        
        // 1. 호출할 때 마다 생성하는지 조회
        MemberService memberService1 = appConfig.memberService();

        // 2. 호출할 때 마다 생성하는지 조회
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른지 확인해보기
        System.out.println("memberService1" + memberService1);
        System.out.println("memberService2" + memberService2);

        // 서로 다른 객체가 생성되므로 메모리 사용이 비효율적이다.
        assertThat(memberService1).isNotSameAs(memberService2);

        // 이 문제의 해결방안은, 하나의 객체를 생성하여 공유할 수 있도록 설계하는 것이다.
    }


    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        // SingletonService singletonService1 = new SingletonService(); // 객체를 외부에서 생성할 수 없다.
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);

        // sameAs : == 비교
        // equals :
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void SingletonContainer() {
//        AppConfig appConfig = new AppConfig();

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 호출할 때 마다 같은 인스턴스를 조회하는지 확인해보기
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조값이 다른지 확인해보기
        System.out.println("memberService1" + memberService1);
        System.out.println("memberService2" + memberService2);

        // 서로 다른 객체가 생성되므로 메모리 사용이 비효율적이다.
        assertThat(memberService1).isSameAs(memberService2);
    }
}
