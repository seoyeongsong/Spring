package hello.core.beanFind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    /* 스프링 컨테이너에 등록된 모든 빈을 출력
    * 개발자가 필요에 의해 등록한 것 뿐 아니라 내부적으로 사용하는 빈 포함하여 출력
    *  */
    void findAllBeans() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }
    }


    @Test
    @DisplayName("어플리케이션 빈 출력하기")
    void findAppBeans() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDef = ac.getBeanDefinition(beanDefinitionName);
        // ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
        // ROLE_INRFRASTRUCTURE : 스프링이 내부에서 사용하는 빈    
            if(beanDef.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }
        }
    }
}
