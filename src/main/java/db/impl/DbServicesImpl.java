package db.impl;

import db.DbServices;
import models.Course;
import models.Students;
import models.Teachers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class DbServicesImpl implements DbServices {
    SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();

    public void saveCourses(Course courses) {
        Session session=sessionFactory.openSession();
        try {
           session.getTransaction().begin();
           session.save(courses);
           session.getTransaction().commit();
       }catch (Exception ex){
            session.getTransaction().rollback();
            ex.printStackTrace();
        }finally {
            session.close();
        }

    }

    public void saveStudents(Students students) {
        Session session=sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            session.save(students);
            session.getTransaction().commit();
        }catch (Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }

    public void saveTeacher(Teachers teachers) {
        Session session=sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            session.save(teachers);
            session.getTransaction().commit();
        }catch (Exception ex){
            session.getTransaction().rollback();
        }finally {
            session.close();
        }

    }

    @Override
    public List<Course> getCourses() {
        Session session=sessionFactory.openSession();
        List<Course>coursesList=new ArrayList<>();
        try {
            coursesList=session.createQuery("from Course",Course.class).list();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return coursesList;
    }


    @Override
    public List<Teachers> getTeachers() {
        Session session=sessionFactory.openSession();
        List<Teachers>teachersList=new ArrayList<>();
        try {
            teachersList=session.createQuery("from Teachers",Teachers.class).list();
            return teachersList;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return teachersList;
    }

    @Override
    public List<Students> getStudents() {
        Session session=sessionFactory.openSession();
        List<Students>studentsList=new ArrayList<>();
        try {
            studentsList=session.createQuery("from Students",Students.class).list();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }return studentsList;
    }
}
