package lec25;

import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) {

        // ArrayList 객체 생성
        ArrayList<String> list = new ArrayList<String>();
        System.out.println("list.size : " + list.size());

        // 데이터 추가
        list.add("Hello");
        list.add("Java");
        list.add("World");
        System.out.println("list.size : " + list.size());
        System.out.println("list : " + list);
        // 2번째 인덱스에 해당 문자열을 삽입한다.
        list.add(2, "Programming");
        System.out.println("list : " + list);
        // 1번째 인덱스에 있는 문자를 바꾼다.
        list.set(1, "Python");
        System.out.println("list : " + list);

        // 데이터 추출 -> 원본에서 삭제되지 않고, 해당 인덱스에 문자가 무엇인지 추출한다.
        String str = list.get(1);
        System.out.println("list.get(1) : " + str);
        System.out.println("list : " + list);

        // 데이터 제거
        str = list.remove(1);
        System.out.println("list.remove(1) : " + str);
        System.out.println("list : " + list);

        // 데이터 전체 제거
        list.clear();
        System.out.println("list : " + list);

        // 데이터 유무
        boolean b = list.isEmpty();
        System.out.println("list.isEmpty() : " + b);

        System.out.println("===================================");
    }
}
