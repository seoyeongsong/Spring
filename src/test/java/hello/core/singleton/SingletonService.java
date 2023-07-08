package hello.core.singleton;

public class SingletonService {

    // static 영역에 객체를 1개 생성한다.
    private static final SingletonService instance = new SingletonService();

    
    // 객체를 조회할 때는 아래 함수를 호출해서만 가능하다.
    public static SingletonService getInstance() {
        return instance;
    }

    // 접근 제한자를 private으로 두어 외부에서 new 생성자를 통해 접근할 수 없다.
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
