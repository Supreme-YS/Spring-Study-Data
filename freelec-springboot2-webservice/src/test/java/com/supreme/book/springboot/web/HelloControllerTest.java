package com.supreme.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈(Bean)을 주입 받는다.
    private MockMvc mvc; // 웹 API를 테스트할 때 사용, 스프링 MVC 테스트의 시작점, 이 클래스를 통해 HTTP GET, POST등에 대한 API 테스트 가능

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        // 코드 체이닝을 통한 연속 검증
        mvc.perform(get("/hello")) // MockMVC를 통해 /hello 주소로 HTTP GET 요청을 함, 체이닝이 지원되어 여러개 검증 가능
                .andExpect(status().isOk()) // mvc.perform의 결과를 검증 , 200/404/500등의 상태 검증, OK는 200인지 검증
                .andExpect(content().string(hello)); // HelloController에서 hello를 반환하기 때문에 이 값이 맞는지 검증
    }

    // 두 번째 테스트 코드
    @Test
    public void hello2가_리턴된다() throws Exception {
        String hello2 = "hello2";

        mvc.perform(get("/hello2"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello2));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name) // api 테스트할 때 사용될 요청 파라미터를 설정한다. 단. 값은 String만 허용
                .param("amount", String.valueOf(amount))) // 따라서, 문자열로 변경해야만 사용 가능하다.
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // json 응답값을 필드별로 검증할 수 있는 메소드
                .andExpect(jsonPath("$.amount", is(amount))); // $를 기준으로 필드명을 명시한다.
        // 여기서는 name과 amount 필드를 검증하므로 $.name , $.amount 라고 작성
    }
}