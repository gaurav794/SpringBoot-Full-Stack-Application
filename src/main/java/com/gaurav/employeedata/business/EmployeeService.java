package com.gaurav.employeedata.business;

import com.gaurav.employeedata.entity.Employee;
import com.gaurav.employeedata.repository.EmployeeRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployee() {
        Iterable<Employee> employees = this.employeeRepository.findAll();

        List<Employee> employeeList1 = Streamable.of(employees).toList();

//        List<Employee> employeeList = new ArrayList<>();
//        employees.forEach(employee -> employeeList.add(employee));
        return employeeList1;
    }

    public Employee getEmployeeById(Long id) {
        Employee employee;
        if((employee = this.employeeRepository.findById(id).get()) == null)
        {
            throw new RuntimeException("Employee not found");
        }
        return employee;
    }

    public void addEmployee(Employee employee) {
        if (employee == null) {
            throw new RuntimeException("Employee data cannot be empty");
        }
        this.employeeRepository.save(employee);
    }

    public void updateEmployee(Long id, Employee updateEmployee) {

        if(this.employeeRepository.findById(id).isPresent() && (updateEmployee != null))
        {
           Employee existingEmployee = this.employeeRepository.findById(id).get();
           existingEmployee.setFirstName(updateEmployee.getFirstName() == null ? existingEmployee.getFirstName():updateEmployee.getFirstName());
           existingEmployee.setLastName(updateEmployee.getLastName() == null ? existingEmployee.getLastName(): updateEmployee.getLastName());
           existingEmployee.setEmailId(updateEmployee.getEmailId() == null ? existingEmployee.getEmailId():updateEmployee.getEmailId());
           existingEmployee.setOrganizationName(updateEmployee.getOrganizationName() == null ? existingEmployee.getOrganizationName(): updateEmployee.getOrganizationName());
           this.employeeRepository.save(existingEmployee);
        }
        else {
            throw new RuntimeException("Employee not found");
        }
    }

    public void deleteEmployee(Long id) {
        if(this.employeeRepository.findById(id).isPresent())
        {
            this.employeeRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Employee not found");
        }
    }
}
