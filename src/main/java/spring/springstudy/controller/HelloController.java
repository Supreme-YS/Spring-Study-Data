package spring.springstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
        // resources -> templates -> hello.html 로 가서 렌더링 진행
        // 컨트롤러에서 리턴 값으로 문자를 반환하면 viewResolver가 화면을 찾아서 처리한다.
        // viewName 매핑 = 'resources:templates/'+{ViewName}+'.html'
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // Http 통신 body 부에 return 데이터를 직접 넣어주겠다는 뜻
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
        // 이후 페이지 소스보기를 하게 되면 html 코드들이 보이지 않고, return 값만 보이게 된다.
        // hello-mvc와 차이점.
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    //@ResponseBody를 통해 viewResolver대신 HttpMessageConverter가 동작
    //HttpMessageConverter에서 넘어오는 데이터 유형을 파악
    //객체 형식 -> jsonConverter -> json 반환
    //string 형식 -> StringConverter -> str 반환
    //기본 문자처리: StringHttpMessageConverter
    //기본 객체처리: MappingJackson2HttpMessageConverter
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

