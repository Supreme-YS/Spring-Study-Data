package lec08;

public class Casting2 {
    public static void main(String[] args) {

        // 자동 형 변환 : 작은 공간의 메모리에서 큰 공간의 메모리로 이동
       byte by = 10;
        int in = by;
        System.out.println("in = "+ in);

        // 명시적 형 변환 : 큰 공간의 메모리에서 작은 공간의 메로리로 이동
        int iVar = 100;
        byte bVar = (byte)iVar;
        System.out.println(bVar);

        // 명시적 형 변환은 데이터 유실의 위험성이 있다.
        iVar = 123456;
        bVar = (byte)iVar;
        System.out.println("bVar = "+ bVar);

    }
}
