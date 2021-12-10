package lec20;

public class FirstChildClass extends ParentClass {

    public FirstChildClass() {
        System.out.println("--FirstChildClass Constructor--");
    }

    @Override
    public void makeBread() {
        System.out.println("--FirstChildClass's more delicious makeBread() method START--");
    }

}
