package org.example.btvnbuoi4.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "email", unique = true, length = 250)
    private String email;

    @Column(name = "phone", length = 11)
    private String phone;

    @ManyToMany
    @JoinTable(
          name = "student_class",
          joinColumns = @JoinColumn(name = "student_id"),
          inverseJoinColumns = @JoinColumn(name = "class_id")
    )
    private List<Class> classes = new ArrayList<Class>();

}
