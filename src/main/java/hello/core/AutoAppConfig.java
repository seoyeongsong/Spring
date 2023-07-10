package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// 기존에 생성한 AppConfig에 등록한 스프링 빈을 중복으로 등록하지 않기 위해 예외 조건을 명시해준다.
@ComponentScan(
        // scan 시작 위치를 설정할 수 있다.
//        basePackages = "hello.core.member",
//        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {


}
