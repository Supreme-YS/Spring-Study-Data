package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 1. 원격호출 가능한 프로그램으로 등록
@Controller
public class Hello {
	
	int iv = 10;
	static int cv = 20;
	
	// 2. URL과 메서드를 연결
	@RequestMapping("/hello")
	private void main() { // 인스턴스 메서드 - iv, cv 둘 다 가능
		System.out.println("Hello - private");
		System.out.println(iv);
		System.out.println(cv);
	}
	
	public static void main2() { // static 메서드 - cv만 가능
//		System.out.println(iv); // error
		System.out.println(cv); // ok
	}
}
