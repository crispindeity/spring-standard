package study.springstandard.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Hello {
    int i = 10;
    static int si = 30;

    @RequestMapping("/hello")
    private void main() {
        System.out.println("Hello");
        System.out.println("i = " + i);
        System.out.println("si = " + si);
    }
}
