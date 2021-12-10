package lec19;

public class ParentClass {
    // 디폴트 생성자 (System.out.println() 없을 때, 자기 자신을 이름으로
    public ParentClass() {
        System.out.println("--ParentClass Constructor--");
    }

    public void parentFun() {
        System.out.println("--parentFun() start--");
    }

    private void privateFun() {
        System.out.println("--privateFun() start--");
    }

    public void makeBread() {
        System.out.println("--makeBread() start--");
    }
}
