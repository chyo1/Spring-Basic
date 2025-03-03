package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import javax.print.DocFlavor.READER;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration

// 이전에 만들어뒀던 TestConfig 등의 정보들을 컴포넌트 스캔 대상에서 제외하기 위해 filter 걸어줌
// @Configuration도 @Component 어노테이션이 붙어 있기에 스캔이 가능
@ComponentScan(
        // 탐색할 패키지의 시작 위치 지정, 해당 패키지부터 하위 패키지를 모두 탐색
        // default : @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치로 지정됨, 권장 방법
        basePackages = "hello.core",
        excludeFilters =
        @Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {

        // 이 경우 수동 빈 등록이 우선권을 가짐 (수동 빈이 자동 빈을 오버라이딩 함)
        // 스프링 부트는 수동 빈 등록 시에도 중복 에러를 발생, 애매한 상황을 방지함
        @Bean(name = "memoryMemberRepository")
        public MemberRepository memberRepository() {
                return new MemoryMemberRepository();
        }

}
