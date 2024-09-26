package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // main > resources > static > index.html이 있어도 @GetMapping("/")이 있다면 우선순위이다.
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
