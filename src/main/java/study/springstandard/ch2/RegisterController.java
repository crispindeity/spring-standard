package study.springstandard.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String registerForm() {
        return "register/registerForm";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterInfo registerInfo) {
        System.out.println("registerInfo = " + registerInfo);
        return "redirect:/";
    }
}
