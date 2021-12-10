package lec04;

import java.util.Scanner;

public class Scan2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // 1. 작은 정수 , 21억까지만 가능
        int number01 = scan.nextInt();
        System.out.println(number01);

        // 2. 큰 정수
        long number02 = scan.nextLong();
        System.out.println(number02);

        // 3. 작은 실수
        float number03 = scan.nextFloat();
        System.out.println(number03);

        // 4. 큰 실수
        double number04 = scan.nextDouble();
        System.out.println(number04);

        // 5. 문장과 숫자를 각각 입력받는 방법
        String name = scan.nextLine();
        int age = scan.nextInt();

        System.out.println("이름 :" + name);
        System.out.println("나이 :" + age);
    }
}
