package com.supreme.book.springboot.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
// Junit의 assertThat보다 assertj의 assertThat이 갖는 장점
// Junit의 assertThat은 is()와 같이 CoreMatchers 라이브러리가 필요하다. 즉 추가적인 라이브러리가 불필요함
// 자동완성이 좀 더 확실하게 지원된다.

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name); // assertj 라는 테스트 검증 라이브러리의 검증 메소드
        assertThat(dto.getAmount()).isEqualTo(amount); // 검증하고 싶은 대상을 메소드 인자로 받고, 메소드 체이닝이 지원된다.
        // assertThat에 있는 값과 isEqualTo의 값을 비교해서 같을 때만 성공.
    }
}
