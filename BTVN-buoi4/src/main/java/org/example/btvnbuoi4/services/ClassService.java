package org.example.btvnbuoi4.services;

import org.example.btvnbuoi4.models.Class;
import org.example.btvnbuoi4.models.Student;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ClassService {
    public List<Class> getAllClasses(int page, int size, Sort sort);
    public Class getClassById(int id);
    public Class saveClass(Class classN);
    public void deleteClass(Integer id);
}
