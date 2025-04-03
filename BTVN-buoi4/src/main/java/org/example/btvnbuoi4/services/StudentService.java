package org.example.btvnbuoi4.services;

import org.example.btvnbuoi4.models.Student;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface StudentService {
    public List<Student> getAllStudents(int page, int size, Sort sort);
    public Student getStudentById(int id);
    public Student saveStudent(Student student);
    public void deleteStudent(Integer id);
}
