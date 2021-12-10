package lec24;

public class MainClass {
    public static void main(String[] args) {

//        String str = "JAVA";
        String str = new String("JAVA");
        System.out.println("str : " + str);
        str = str + "_8";
        System.out.println("str : " + str);

        // StringBuffer
        StringBuffer sf = new StringBuffer("JAVA");
        System.out.println("sf : " + sf);

        sf.append(" world");
        System.out.println("sf : " + sf);
        System.out.println("sf.length() : " + sf.length());
        sf.insert(sf.length(), " 추가되는 구문");
        System.out.println("sf.insert() : " + sf);
        sf.delete(4, 8);
        System.out.println("sf.delete() : " + sf);

        StringBuilder sb = new StringBuilder("JAVA");
        System.out.println("sb : " + sb);
    }
}
