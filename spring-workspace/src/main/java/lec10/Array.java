package lec10;

import java.util.ArrayList;
import java.util.Arrays;

public class Array {
    public static void main(String[] args) {
        int[] array1 = new int[5]; // 배열 선언 단계
        array1[0] = 10;
        array1[1] = 20;
        array1[2] = 30;
        array1[3] = 40;
        array1[4] = 50;

        // 배열 선언과 초기화를 동시에 하기
        int[] array2 = {10, 20, 30, 40, 50};

        // 객체 자료형이기 때문에 메모리의 주소를 담고있다. 기본 자료형 vs 객체 자료형
        System.out.println(array1);
        System.out.println(array2);

        // 배열의 요소 출력
        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));

        // multi-dimension arrays
        int[][] multiArray = new int[2][2]; // 2행 2열
        multiArray[0][0] = 1;
        multiArray[0][1] = 2;
        multiArray[1][0] = 3;
        multiArray[1][1] = 4;

        System.out.println(multiArray);

        for(int i=0; i<multiArray.length; i++) {
            System.out.println();

            for(int j=0; j<multiArray[i].length; j++) {
                System.out.print(multiArray[i][j] + " ");
            }
        }
    }
}
