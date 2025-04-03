package org.example.btvnbuoi4.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Tên lớp không được để trống")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "subject")
    private String subject;

    @ManyToMany(mappedBy = "classes")
    private List<Student> students = new ArrayList<Student>();
}
