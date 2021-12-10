package lec21;

public class InterfaceClass implements InterfaceA, InterfaceB {
    // 생성자 생성
    public InterfaceClass() {
        System.out.println("InterfaceClass Constructor");
    }

    @Override
    public void funA() {
        System.out.println("funA()");
    }

    @Override
    public void funB() {
        System.out.println("funB()");
    }
}
