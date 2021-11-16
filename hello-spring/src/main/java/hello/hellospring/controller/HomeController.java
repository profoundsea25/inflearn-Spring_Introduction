package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // 먼저 스프링 컨테이너를 찾아보고, 없으면 static 폴더를 찾는다.
    // 그래서 index.html을 불러오지 않음
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
