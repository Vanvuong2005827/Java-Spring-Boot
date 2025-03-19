package org.example.btvnbuoi2.services;

import org.example.btvnbuoi2.entities.Employee;
import org.example.btvnbuoi2.respository.EmployeeRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRespository employeeRespository;

    public EmployeeService(EmployeeRespository employeeRespository) {
        this.employeeRespository = employeeRespository;
    }

    //get
        //getall

    public List<Employee> getAllEmployees() {
        return employeeRespository.findAll();
    }
        //getone
    public Employee getEmployeeById(int id) {
        return employeeRespository.findById(id).get();
    }

    //post
    public void addEmployee(Employee employee) {
        employeeRespository.save(employee);
    }

    //update
    public void updateEmployee(Employee employee) {
        employeeRespository.save(employee);
    }

    //delete
    public void deleteEmployee(int id) {
        employeeRespository.deleteById(id);
    }
}
