package lec17;

public class MainClass {
    public static void main(String[] args) {
        ObjectEx obj1;
        obj1 = new ObjectEx();
        obj1 = new ObjectEx();

        // garbage collector 호출 - 쓰지않는 객체 삭제
        System.gc();
    }
}
