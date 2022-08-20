package com.gaurav.employeedata.webservice;

import com.gaurav.employeedata.entity.Employee;
import com.gaurav.employeedata.business.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {


    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(path="/employees", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployee() {
        List<Employee> employees = this.employeeService.getEmployee();
        return employees;
    }

    @RequestMapping(path="/employee", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(@RequestParam (value = "id",required = true) Long id) {
        return this.employeeService.getEmployeeById(id);
    }

    @RequestMapping(path="add", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addEmployee(@RequestBody Employee employee) {
        this.employeeService.addEmployee(employee);
    }

    @RequestMapping(path="update", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void updateEmployee(@RequestParam(value="id",required=true) Long id,@RequestBody Employee employee) {
        this.employeeService.updateEmployee(id, employee);
    }

    @RequestMapping(path="/delete", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployeeById(@RequestParam (value = "id",required = true) Long id) {
        this.employeeService.deleteEmployee(id);
    }


}
