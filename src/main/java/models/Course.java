package models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import javax.persistence.Entity;
@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private LocalDate dayStart;
    @OneToOne
    private Teachers teachersId;
    private int active=1;
    private int num=1;
}
