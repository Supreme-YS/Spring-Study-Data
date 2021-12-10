package com.supreme.book.springboot.web.dto;

// HelloController 코드를 롬복으로 전환하기
// 테스트 코드의 중요성을 알 수 있다.
// DTO 패키지에 모든 응답을 기록한다.

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// @Getter 선언된 모든 필드의 get 메소드를 생성시켜준다.
@Getter
// @RequiredArgsConstructor 선언된 모든 final 필드가 포함된 생성자를 생성시켜준다.
// final이 없는 필드는 생성자에 포함되지 않는다.
@RequiredArgsConstructor
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
