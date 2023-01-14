package springbootwebdemo2.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import java.util.Arrays;

@Controller
public class IndexThymeleafController {

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("msg", "hello, thymeleaf");
        model.addAttribute("users", Arrays.asList("wyj", "mary"));
        return "index";
    }

}
