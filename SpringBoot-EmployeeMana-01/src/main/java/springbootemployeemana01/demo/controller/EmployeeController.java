package springbootemployeemana01.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springbootemployeemana01.demo.dao.EmployeeDao;
import springbootemployeemana01.demo.pojo.Employee;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;
    
    @RequestMapping("/emps")
    public String list(Model model) {
        // ctrl+alt+v 自动补全函数返回值
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps", employees);
        return "emp/list";
    }
}
