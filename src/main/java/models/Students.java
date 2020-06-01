package models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import javax.persistence.Entity;

@Entity
@Data
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String address;
    @ManyToMany
    @JoinTable( name = "curses_students",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns=@JoinColumn(name = "courses_id") )
    private List<Course>coursesID;
}
