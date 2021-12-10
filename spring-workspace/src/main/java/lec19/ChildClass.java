package lec19;

public class ChildClass extends ParentClass {

    public ChildClass() {
        System.out.println("--ChildClass Constructor--");
    }

    public void childFun() {
        System.out.println("--childFun() start--");
    }
    @Override
    public void makeBread() {
        System.out.println("--more delicious makeBread() start--");
    }
}
