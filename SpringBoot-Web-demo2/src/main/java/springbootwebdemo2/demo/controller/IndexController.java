package springbootwebdemo2.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/index1")
    public String index1() {
        return "index1";
    }
}


