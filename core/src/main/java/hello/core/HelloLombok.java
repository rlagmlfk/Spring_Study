package hello.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
// @NoArgsConstructor 생성자 관련 롬복지원
// @ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("abc");
        String name = helloLombok.getName();
        System.out.println("name = "+name);
    }
}
