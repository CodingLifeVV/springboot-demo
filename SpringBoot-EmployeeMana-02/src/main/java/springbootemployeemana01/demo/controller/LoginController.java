package springbootemployeemana01.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController {
    @RequestMapping("/user/login")
    //@ResponseBody
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model, HttpSession session) {
        // 具体业务
        if (!StringUtils.isEmpty(username) && password.equals("123456")) {
            session.setAttribute("loginUser", username);
            //获取用户信息保存在 session 中,在拦截器类中拿到信息进行权限判断
            session.setAttribute("loginUser", username);
            //return "dashboard";
            return "redirect:/main.html";
        } else {
            // 用户名密码错误
            model.addAttribute("msg", "用户名或者密码错误！");
            return "index";
        }
    }

    @RequestMapping("/user/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index.html";
    }
}













