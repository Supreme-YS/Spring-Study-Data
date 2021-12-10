package lec12;

public class Bicycle {

    public String color;
    public int price;

    public Bicycle() {
        System.out.println("Bicycle constructor");
    }

    public Bicycle(String c, int p) {
        System.out.println("Bicycle constructor-II");
    }

    public void info() {
        System.out.println("---info---");
        System.out.println("color : " + color);
        System.out.println("price : " + price);
    }
}
