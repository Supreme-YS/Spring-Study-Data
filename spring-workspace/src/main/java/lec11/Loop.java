package lec11;

import java.util.Scanner;

public class Loop {
    public static void main(String[] args) {
        System.out.print("숫자를 입력하세요 : ");
        Scanner scanner = new Scanner(System.in);
        int inputNum = scanner.nextInt();

        for(int i = 1; i < 10; i++) {
            System.out.printf("%d * %d = %d\n", inputNum, i, (inputNum * i));
        }
    }
}