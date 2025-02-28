package hello.core.singleton;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        statefulService1.order("A", 100);
        statefulService1.order("B", 200);

        int priceA = statefulService1.getPrice();
        System.out.println("priceA = " + priceA);

        // userA의 price는 100이어야 하는데 statefulService의 price 필드가 공유되므로 200이 되었다
        // 스프링 빈은 항상 무상태(stateless)로 설계해야 함!
        // -> price를 내부 필드로 가지고 있는 게 아닌 반환하는 형식으로 바꾼다면 무상태
        assertThat(statefulService1.getPrice()).isEqualTo(200);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}