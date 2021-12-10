package lec11;

import lec04.Switch;

import java.util.Scanner;

public class Conditional {
    public static void main(String[] args) {
        // 양자택일 : 주로 if 문
        // 다자택일 : 주로 switch 문

        int num1 = 10;
        int num2 = 20;

        // if(조건식)
        if (num1 < num2) {
            System.out.println("num1 은 num2보다 작다.");
        }
        System.out.println();

        if (num1 > num2) {
            System.out.println("num1 은 num2보다 크다.");
        } else {
            System.out.println("num1 은 num2보다 작다.");
        }

        System.out.println();

        if (num1 > num2) {
            System.out.println("num1 은 num2보다 크다.");
        } else if (num1 < num2) {
            System.out.println("num1 은 num2보다 작다.");
        } else {
            System.out.println("num1 과 num2는 같다.");
        }

        // Switch 문

        System.out.print(" 점수를 입력하세요 : ");
        Scanner inputNum = new Scanner(System.in);
        int score = inputNum.nextInt();

        switch (score) {
            case 100:
            case 90:
                System.out.println("수");
                break;
            case 80:
            case 70:
                System.out.println("우");
                break;
            case 60:
            case 50:
                System.out.println("미");
                break;

            default:
                System.out.println("재시도");
                break;
        }

        inputNum.close();
    }
}
