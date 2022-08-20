package com.gaurav.employeedata.web;

import com.gaurav.employeedata.entity.Employee;
import com.gaurav.employeedata.business.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getEmployee(Model model) {
        List<Employee> employees = this.employeeService.getEmployee();
        model.addAttribute("employees", employees);
        return "view";
    }

}
