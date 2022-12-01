package hello.core.singleton;

public class SingletonService {

    // 자기자신을 내부의 static으로 인스턴스화
    // -> 클래스 레벨로 올라가기 때문에 딱 하나만 존재하게 됨
    private static final SingletonService instance = new SingletonService();

    // 이 객체 인스턴스가 필요하면 getInstance() 메서드를 통해서만 조회할 수 있음
    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService(){

    }
    
    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
    
}
