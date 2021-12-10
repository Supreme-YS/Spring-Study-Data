package lec07;

public class Casting {
    public static void main(String[] args) {

        double a = 1.1 ;
        double b = 1 ;
        double b2 = (double) 2 ;

        System.out.println(b);

//      int c = 1.1; # loss가 발생하므로 미리 경고를 발생시켜준다.
        double d = 1.1;
        int e = (int) 1.1 ;

        System.out.println(e);

//      1 to String
        String f = Integer.toString(1);
        System.out.println(f);
        System.out.println(f.getClass());
    }
}
