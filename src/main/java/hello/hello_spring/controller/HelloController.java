package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello")        // 웹 어플리케이션에서 /hello 로 들어오면 아래 메서드를 호출해준다는 의미이다.
    public String hello(Model model) {      // model ⇒ MVC
        model.addAttribute("data", "hello!!");
        return "hello";
    }
}
