package hello.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;



/*
* @ServletComponentScan
* 스프링부트에서 서블릿을 직접 등록해서 사용할 수 있게하는 Annotation
* */
@ServletComponentScan

@SpringBootApplication
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}

}
