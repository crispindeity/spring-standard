package study.springstandard.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Hello {
    int i = 10;
    static int si = 30;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    private void main() {
        System.out.println("Hello");
        System.out.println("i = " + i);
        System.out.println("si = " + si);
    }
}
