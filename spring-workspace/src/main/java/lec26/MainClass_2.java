package lec26;

public class MainClass_2 {
    public static void main(String[] args) {

        MainClass_2 mainClass_2 = new MainClass_2();

        try {
            mainClass_2.firstMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void firstMethod() throws Exception {
        secondMethod();
    }
    public void secondMethod() throws Exception {
        thirdMethod();
    }
    public void thirdMethod() throws Exception {
        System.out.println("10 / 0 = " + ( 10 / 0 ));
    }
}
