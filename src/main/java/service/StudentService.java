package service;

import dao.spring.StudentSpringDAO;
import data.student.Student;
import test.Context;


public class StudentService {

    public boolean createStudent(Student student){
        boolean result= Context.getInstance().getBean("studentSpringDAO", StudentSpringDAO.class).insertStudent(student);
        return result;
    }

    public boolean deleteStudent(Student student){
        boolean result= Context.getInstance().getBean("studentSpringDAO", StudentSpringDAO.class).deleteStudent(student.getName());
        return result;
    }
}
