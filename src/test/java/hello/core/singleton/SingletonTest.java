package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);

        // 이 문제의 해결방안은, 하나의 객체를 생성하여 공유할 수 있도록 설계하는 것이다.
    }

}
