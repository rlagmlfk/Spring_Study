package hello.core.singleton;

public class StatefulService {

    // private int price; // 상태를 유지하는 필드

/*
    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; // 여기가 문제!

    }
*/
    
    // 스프링은 항상 무상태(stateless)로 설계해야 한다
    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        // this.price = price; // 여기가 문제!
        return price;
    }

    // public int getPrice() {
    //     return price;
    // }
}
