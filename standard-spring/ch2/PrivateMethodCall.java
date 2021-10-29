package com.fastcampus.ch2;

import java.lang.reflect.Method;

// private된 클래스를 외부에서 접근할 수 있게 하는 방법

public class PrivateMethodCall {
	public static void main(String[] args) throws Exception {
//		Hello hello = new Hello();
//		hello.main(); // private이라서 외부 호출 불가
		
		// Reflection API를 사용 - 클래스 정보를 얻고 다룰 수 있는 강력한 기능
		// java.lang.reflect 패키지를 제공
		// Hello 클래스의 Class객체(클래스의 정보를 담고 있는 객체)를 얻어온다.
		Class HelloClass = Class.forName("com.fastcampus.ch2.Hello"); // 클래스 파일(.class)이 메모리에 올라갈 때 클래스 파일마다 Class 객체가 하나씩 생성된다.
		Hello hello = (Hello)HelloClass.newInstance(); // Class 객체가 가진 정보로 객체 생성
		Method main = HelloClass.getDeclaredMethod("main");
		main.setAccessible(true); // private인 main()을 호출가능하게 한다.
		
		main.invoke(hello); // hello.main()
	}
}
