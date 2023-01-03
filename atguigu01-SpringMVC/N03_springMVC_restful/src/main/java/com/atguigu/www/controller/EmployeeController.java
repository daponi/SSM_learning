package com.atguigu.www.controller;

import com.atguigu.www.dao.EmployeeDao;
import com.atguigu.www.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * 查询所有的员工信息-->/employee-->get
 * 跳转到添加页面-->/to/add-->get
 * 新增员工信息-->/employee-->post
 * 跳转到修改页面-->/employee/1-->get
 * 修改员工信息-->/employee-->put
 * 删除员工信息-->/employee/1-->delete
 */

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    @GetMapping("/employee")
    public String getAllEmployee(Model model){
        //获取员工数据
        Collection<Employee> allEmployee = employeeDao.getAll();
        // 将所有的员工信息共享在请求域中
        model.addAttribute("allEmployee",allEmployee);
        //跳转到列表页面
        return "employee_list";
    }

    @PostMapping("/employee")
    public String addEmployee(Employee employee){
        //保存员工信息
        employeeDao.save(employee);
        //重定向到列表功能:/employee
        return "redirect:/employee";
    }

    @GetMapping("/employee/{id}")
    public String getEmployeeById(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("employee",employee);
        return "employee_update";
    }

    @PutMapping("/employee")
    public String updateEmployeeById(Employee employee){
        employeeDao.save(employee);
        return "redirect:/employee";
    }

    @DeleteMapping("/employee/{id}")
    public String deleteEmployeeById(@PathVariable("id") Integer id ){
        employeeDao.delete(id);
        //重定向到列表功能
        return "redirect:/employee";
    }

}
