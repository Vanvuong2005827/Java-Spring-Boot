package org.example.btvnbuoi2.controller;

import org.example.btvnbuoi2.entities.Employee;
import org.example.btvnbuoi2.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //get
    @GetMapping("/employees")
    public String employees(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("index", employeeService.getAllEmployees());
        return "index";
    }

    //post
    @PostMapping("/save")
    public String save(Employee employee) {
        employeeService.addEmployee(employee);
        return "redirect:/employees";
    }

    //put
    @PostMapping("/edit")
    public String edit(Employee employee) {
        employeeService.updateEmployee(employee);
        return "redirect:/employees";
    }

    //delete
    @PostMapping("/delete")
    public String delete(@RequestParam("id")int id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }

    @PostMapping("/deletes")
    public String delete(@RequestParam("ids") List<Integer> ids) {
        for(int x : ids) {
            employeeService.deleteEmployee(x);
        }
        return "redirect:/employees";
    }
}
