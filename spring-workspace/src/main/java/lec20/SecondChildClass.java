package lec20;

public class SecondChildClass extends ParentClass{
    public SecondChildClass() {
        System.out.println("--SecondChildClass Constructor--");
    }

    @Override
    public void makeBread() {
        System.out.println("--SecondChildClass's more delicious makeBread() method START--");
    }

}
