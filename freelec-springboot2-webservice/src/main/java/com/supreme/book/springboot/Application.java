package com.supreme.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication으로 인해, 스프링 부트의 자동설정, 스프링 Bean 읽기와 생성을 자동으로 설정
// Application이 있는 위치부터 설정을 읽는다. 따라서, 항상 프로젝트 최상단에 위치해야 한다.
@SpringBootApplication
// 메인 클래스
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
