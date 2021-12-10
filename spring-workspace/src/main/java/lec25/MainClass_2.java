package lec25;

import java.util.HashMap;

public class MainClass_2 {
    public static void main(String[] args) {
        // HashMap 객체 생성
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        System.out.println("map.size() : " + map.size());
        // 데이터 추가
        map.put(5, "Hello");
        map.put(6, "Java");
        map.put(7, "World");
        System.out.println("map : " + map);
        System.out.println("map.size() : " + map.size());
        map.put(8, "!");
        System.out.println("map : " + map);
        System.out.println("map.size() : " + map.size());

        // 데이터 교체
        map.put(6, "Python");
        System.out.println("map : " + map);
        System.out.println("map.size() : " + map.size());

        // 데이터 추출
        String str = map.get(6);
        System.out.println("map.get(6) : " +str);

        // 데이터 제거
        map.remove(8);
        System.out.println("map.remove(8) : " + map);

        // 특정 데이터 포함 유무
        boolean b = map.containsKey(7);
        System.out.println("map.containsKey(7) : " + b);
        boolean c = map.containsValue("Python");
        System.out.println("map.containsValue(\"Python\") : " + c);

        // 전체 데이터 제거
        map.clear();
        System.out.println("map.clear() : " + map);

        // 데이터 유무
        b = map.isEmpty();
        System.out.println("map.isEmpty() : " + b);

    }
}
