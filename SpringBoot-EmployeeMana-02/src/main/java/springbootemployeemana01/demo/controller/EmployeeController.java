package springbootemployeemana01.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springbootemployeemana01.demo.dao.DepartmentDao;
import springbootemployeemana01.demo.dao.EmployeeDao;
import springbootemployeemana01.demo.pojo.Department;
import springbootemployeemana01.demo.pojo.Employee;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    
    @RequestMapping("/emps")
    public String list(Model model) {
        // ctrl+alt+v 自动补全函数返回值
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps", employees);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model) {
        Collection<Department> departments = departmentDao.getDepartment();
        model.addAttribute("departments",departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id") Integer id, Model model) {
        //查出原来的数据
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("emp", employee);
        //查出所有部门信息
        Collection<Department> departments = departmentDao.getDepartment();
        model.addAttribute("departments",departments);

        return "emp/update";

    }

    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee) {
        System.out.println(employee.getId());
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @GetMapping("/delemp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
