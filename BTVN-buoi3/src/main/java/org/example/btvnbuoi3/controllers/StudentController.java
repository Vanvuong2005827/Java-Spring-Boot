package org.example.btvnbuoi3.controllers;

import jakarta.validation.Valid;
import org.example.btvnbuoi3.entities.Student;
import org.example.btvnbuoi3.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //get
    @GetMapping("/students/page/{npage:(?:[1-9]|10)}")

    public String students(@PathVariable int npage, Model model) {
        model.addAttribute("students", studentService.getAllStudents(npage - 1, 10));
        model.addAttribute("student", new Student());
        return "new";
    }

    //delete
    @GetMapping("/students/delete/{id}")
    public String delete(@PathVariable Long id){
        studentService.deleteStudent(id);
        return "redirect:/students/page/1";
    }

    //create
    @PostMapping("/student/create")
    public String create(@Valid Student student, Model model) {
        studentService.saveStudent(student);
        return "redirect:/students/page/1";
    }

    //update
    @PostMapping("/students/edit")
    public String update(@Valid Student student){
        studentService.saveStudent(student);
        return "redirect:/students/page/1";
    }
}
