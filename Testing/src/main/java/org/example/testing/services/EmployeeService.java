package org.example.testing.services;

import org.example.testing.entities.Employee;
import org.example.testing.respository.EmployeeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRespository employeeRespository;

    public EmployeeService(EmployeeRespository employeeRespository) {
        this.employeeRespository = employeeRespository;
    }

    //get
    public List<Employee> getAllEmployees() {
        return employeeRespository.findAll();
    }

    public Employee getEmployeeById(int id) {
        return employeeRespository.findById(id).get();
    }

    //set
    public void createEmployee(Employee employee) {
        employeeRespository.save(employee);
    }

    //delete
    public void removeEmployee(Employee employee) {
        employeeRespository.delete(employee);
    }
}
