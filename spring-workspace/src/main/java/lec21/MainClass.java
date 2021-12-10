package lec21;

public class MainClass {
    public static void main(String[] args) {

        InterfaceA ia = new InterfaceClass();
        InterfaceB ib = new InterfaceClass();

        ia.funA();
        ib.funB();
    }
}
