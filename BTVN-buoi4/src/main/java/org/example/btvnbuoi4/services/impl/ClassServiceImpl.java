package org.example.btvnbuoi4.services.impl;

import org.example.btvnbuoi4.common.PostPageRequest;
import org.example.btvnbuoi4.models.Class;
import org.example.btvnbuoi4.repository.ClassRepository;
import org.example.btvnbuoi4.services.ClassService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    ClassRepository classRepository;

    public ClassServiceImpl(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public List<Class> getAllClasses(int page, int size, Sort sort) {
        return classRepository.findAll(PostPageRequest.of(page, size, sort)).getContent();
    }

    @Override
    public Class getClassById(int id) {
        return classRepository.findById(id).orElse(null);
    }

    @Override
    public Class saveClass(Class classN) {
        return classRepository.save(classN);
    }

    @Override
    public void deleteClass(Integer id) {
        classRepository.deleteById(id);
    }
}
