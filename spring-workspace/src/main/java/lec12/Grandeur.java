package lec12;

// 클래스는 멤버 변수(속성), 기능(메서드), 생성자로 구성된다.
public class Grandeur {

    // 멤버 변수 (속성)
    public String color;
    public String gear;
    public int price;

    // 생성자
    // 클래스 이름과 동일한 메서드, 외부에서 호출 시 가장 먼저 호출되는 부분
    public Grandeur() {
        System.out.println("Grandeur Constructor");
    }

    // 기능(메서드)
    // void : return 되는 값이 없을 때 사용.
    public void run() {
        System.out.println("---run---");
    }

    // 기능(메서드)
    public void stop() {
        System.out.println("---stop---");
    }

    public void info() {
        System.out.println("--info--");
        System.out.println("color :" + color);
        System.out.println("gear : " + gear);
        System.out.println("price : " + price);
    }
}
