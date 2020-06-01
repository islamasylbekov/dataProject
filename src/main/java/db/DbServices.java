package db;

import db.impl.DbServicesImpl;
import models.Course;
import models.Students;
import models.Teachers;

import java.util.List;

public interface DbServices {
    DbServices INSTANCE= new  DbServicesImpl();
    void saveCourses(Course courses);
    void saveStudents(Students students);
    void saveTeacher(Teachers teachers);
    List<Course> getCourses();
    List<Teachers>getTeachers();
    List<Students>getStudents();
}
