package lec11;

import java.util.Scanner;

public class Loop2 {
    public static void main(String[] args) {
        System.out.print("숫자를 입력하세요 : ");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();

        int i = 1;
        while (i < 10) {
            System.out.printf("%d * %d = %d\n", num, i, (num*i));
            i++;
        }
    }
}
