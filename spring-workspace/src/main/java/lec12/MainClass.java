package lec12;

public class MainClass {

    public static void main(String[] args) {

        Grandeur myCar1 = new Grandeur();
        myCar1.color = "red";
        myCar1.gear = "auto";
        myCar1.price = 100;

        myCar1.run();
        myCar1.stop();
        myCar1.info();

        Grandeur myCar2 = new Grandeur();
        myCar2.color = "yellow";
        myCar2.gear = "manual";
        myCar2.price = 50;

        myCar2.run();
        myCar2.stop();
        myCar2.info();
    }

}
