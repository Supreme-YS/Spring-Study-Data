package lec20;

public class MainClass {
    public static void main(String[] args) {

        // 기본 자료형처럼 클래스도 자료형이다.
        ParentClass[] pArr = new ParentClass[2];

        // 상위 클래스인 ParentClass를 상속받고 있기 때문에 자료형(datatype)을 ParentClass로 지정해도 상관없다.
        ParentClass fc = new FirstChildClass();
        ParentClass sc = new SecondChildClass();
        // 위와 같은 특성 때문에 배열 특징(같은 자료형 이어야 한다)에 의해 같은 배열에 담을 수 있게 된다.
        pArr[0] = fc;
        pArr[1] = sc;

    }
}
