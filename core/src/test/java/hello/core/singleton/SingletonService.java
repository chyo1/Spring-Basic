package hello.core.singleton;

public class SingletonService {

    // static 영역에 객체를 하나만 생성
    private static final SingletonService instance = new SingletonService();

    // 해당 메서드를 통해서만 SingletonService 객체 조회가 가능
    public static SingletonService getInstance() {
        return instance;
    }

    // 생성자를 private으로 선언, 외부에서 new 키워들르 사용한 객체 생성을 방지
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}

/*
    의존관계상 클라이언트가 구체 클래스에 의존 -> DIP위반
    -> OCP 원칙을 위반할 가능성이 높음 등...
    안티패턴으로 불리기도 함
 */