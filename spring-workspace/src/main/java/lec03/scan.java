package lec03;

import java.util.Scanner;

public class scan {
    public static void main(String[] args) {
        // Input
        // 입력을 받을 스캐너를 만든다. new : 새로운 , System.in : 시스템 안으로 스캔한다.
        Scanner scan = new Scanner(System.in);

        // String : 문장이라는 뜻
        // str : 입력받은 '문장의 이름'
        // scanner.nextLine(); : 한 줄을 입력받는다.
        String str = scan.nextLine();
        System.out.println(str);
    }
}
