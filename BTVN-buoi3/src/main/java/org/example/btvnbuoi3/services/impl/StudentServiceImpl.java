package org.example.btvnbuoi3.services.impl;

import org.example.btvnbuoi3.common.PostPageRequest;
import org.example.btvnbuoi3.entities.Student;
import org.example.btvnbuoi3.respository.StudentRespository;
import org.example.btvnbuoi3.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class StudentServiceImpl implements StudentService {
    private final StudentRespository studentRespository;

    @Autowired
    public StudentServiceImpl(StudentRespository studentRespository) {
        this.studentRespository = studentRespository;
    }

    //get list
    @Override
    public List<Student> getAllStudents(int page, int size ) {
        return studentRespository.findAll(PostPageRequest.of(page, size)).getContent();
    }


    //add - update
    public void saveStudent(Student student) {
        studentRespository.save(student);
    }

    //delete
    public void deleteStudent(Long id) {
        studentRespository.deleteById(id);
    }


}
