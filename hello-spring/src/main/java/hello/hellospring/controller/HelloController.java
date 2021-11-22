package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; // resources/templates/hello.html을 찾아서 간다.
    }

    @GetMapping("/hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { // command + p : 파마미터 옵션
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("/hello-string")
    @ResponseBody // http의 헤더부-바디부가 있는데 바디부에 데이터를 직접 넣어주겠다는 의미이다.
    public String helloString(@RequestParam("name") String name) { // 위의 매핑과는 다르게 Model이 안들어갔음..뷰가 없기때문이다
        return "hello " + name;
    }

    /* JSON 방식으로 데이터를 넘기는 것, API 방식 */
    @GetMapping("/hello-api")
    @ResponseBody
    // http의 body부에 문자 내용을 직접 반환한다.
    // viewResolver대신에 httpMessageConverter가 동작한다
    // 문자면 StringConverter가 객체면 JsonConverter가 동작한다.
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();

        hello.setName(name);
        return hello; // 객체를 넘김
    }

    static class Hello {
        // 자바 빈 규약
        // private로 설정된 것을 꺼내다 쓰려면 메서드를 통해서 가져다 써야한다.
        // 프로퍼티 접근 방식이라고도 한다.
        private String name;

        public String getName() { // 게터
            return name;
        }

        public void setName(String name) { // 세터
            this.name = name;
        }
    }
}
