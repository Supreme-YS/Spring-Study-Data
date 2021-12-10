package lec19;

public class MainClass {
    public static void main(String[] args) {

        ChildClass child = new ChildClass(); // ChildClass는 ParentClass를 상속받고 있기 때문에 디폴트 생성자가 실행되더라도 Parent의 디폴트 생성자가 먼저 실행된다.

        child.parentFun(); // parentFun은 ParentClass를 먼저 생성하고
        child.childFun();
        // child.privateFun(); !error 부모 클래스의 private 속성과 메서드는 사용 불가!

        child.makeBread();
    }
}
