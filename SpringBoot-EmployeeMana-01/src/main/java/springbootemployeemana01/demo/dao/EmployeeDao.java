package springbootemployeemana01.demo.dao;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springbootemployeemana01.demo.pojo.Department;
import springbootemployeemana01.demo.pojo.Employee;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {
    //模拟数据库的数据
    private static Map<Integer, Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<Integer, Employee>();

        employees.put(1001, new Employee(1001, "AA", "1013201176@qq.com", 1, new Department(101, "教学部")));
        employees.put(1002, new Employee(1002, "BB", "2013201176@qq.com", 0, new Department(102, "市场部")));
        employees.put(1003, new Employee(1003, "CC", "3013201176@qq.com", 0, new Department(103, "教研部")));
        employees.put(1004, new Employee(1004, "DD", "4013201176@qq.com", 1, new Department(104, "运营部")));
        employees.put(1005, new Employee(1005, "EE", "5013201176@qq.com", 1, new Department(105, "后勤部")));
    }


    //员工主键自增
    private static Integer initId = 1006;
    // 增加一个员工
    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }
        // 该行代码表明每次增加的员工一定有相对于的部门
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));

        employees.put(employee.getId(), employee);
    }

    // 查询全部员工信息
    public Collection<Employee> getAll() {
        return employees.values();
    }

    // 通过 id 查询员工
    public Employee getEmployeeById(Integer id) {
        return employees.get(id);
    }

    // 删除员工信息通过 id
    public void delete(Integer id) {
        employees.remove(id);
    }
}
