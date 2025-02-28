package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        // 모두 같은 인스턴스를 참조하고 있음, new로 인스턴스를 생성해서 가져 오는 데도
        System.out.println("memberService -> memberRepository = " + memberService.getMemberRepository());
        System.out.println("orderService -> memberRepository = " + orderService.getMemberRepository());
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberService.getMemberRepository()).isEqualTo(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isEqualTo(memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);

        // bean = class hello.core.AppConfig$$SpringCGLIB$$0
        // @Configuration 어노테이션이 AppConfig를 상속받은 임의의 다른 클래스를 만들고, 그 클래스를 스프링 빈으로 등록
        // -> 임의의 클래스가 싱글톤을 보장해줌, @Configuration 없이 @Bean으로만 등록한다면 bean 등록은 되지만 싱글톤이 깨짐
        System.out.println("bean = " + bean.getClass());
    }
}
