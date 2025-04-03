package org.example.btvnbuoi4.services.impl;

import org.example.btvnbuoi4.common.PostPageRequest;
import org.example.btvnbuoi4.models.Student;
import org.example.btvnbuoi4.repository.StudentRepository;
import org.example.btvnbuoi4.services.StudentService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    //get
    @Override
    public List<Student> getAllStudents(int page, int size, Sort sort) {

        return studentRepository.findAll(PostPageRequest.of(page, size, sort)).getContent();
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    //create or update
    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    //delete
    @Override
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }
}
