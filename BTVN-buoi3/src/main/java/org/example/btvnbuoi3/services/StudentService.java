package org.example.btvnbuoi3.services;

import org.example.btvnbuoi3.entities.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getAllStudents(int page, int size);
    public void saveStudent(Student student);
    public void deleteStudent(Long id);
}
