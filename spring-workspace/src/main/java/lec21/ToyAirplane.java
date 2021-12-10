package lec21;

// Toy 인터페이스의 메서드를 정의해주는 클래스
public class ToyAirplane implements Toy {


    @Override
    public void walk() {
        System.out.println("The airplane can't walk");
    }

    @Override
    public void run() {
        System.out.println("The airplane can't run");
    }

    @Override
    public void alarm() {
        System.out.println("The airplane has alarm function");
    }

    @Override
    public void light() {
        System.out.println("The airplane has no light function");
    }
}
