package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    @DisplayName("싱글톤 테스트 ")
    void statefulServiceTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // ThreadA : A 사용자가 10000 주문
        statefulService1.order("userA", 10000);
        
        // ThreadB : B 사용자가 20000 주문
        // 특정 클라이언트가 필드를 변경하여 영향도가 있음
        statefulService2.order("userB", 20000);

        // ThreadA : A사용자 주문 금액을 조회
        int priceA = statefulService1.getPrice();
        System.out.println("priceA = " + priceA);

        // 결과는 A가 주문한 10000원을 B가 같은 인스턴스를 덮어씀으로써 20000원이 출력됨.
        assertThat(priceA).isEqualByComparingTo(20000);

//         assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }

    }

}