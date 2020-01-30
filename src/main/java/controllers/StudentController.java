package controllers;

import data.student.Student;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.StudentService;
import test.Context;


@Controller
@RequestMapping(value = "/students")
public class StudentController {

    @RequestMapping(value = "/student/",method = RequestMethod.POST,headers = {"Content-type=application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Student createStudentJSON (@RequestBody Student student){
       boolean result= new StudentService().createStudent(student);
        if(result) {
           return student;
        }
        else {
           return null;
        }
    }

    @RequestMapping(value = "/student/",method = RequestMethod.DELETE,headers = {"Content-type=application/json"})
    @ResponseStatus(HttpStatus.GONE)
    @ResponseBody
    public String deleteStudentJSON(@RequestBody Student student){
        boolean result= Context.getInstance().getBean("studentService",StudentService.class).deleteStudent(student);
        if(!result){
            return "successful";
        }
        else return "error";
    }

    @RequestMapping(value = "/student/",method = RequestMethod.POST,headers = {"Content-type=application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Student createStudentXML (@RequestBody Student student){
        boolean result= new StudentService().createStudent(student);
        if(result) {
            return student;
        }
        else {
            return null;
        }
    }
    @RequestMapping(value = "/student/",method = RequestMethod.DELETE,headers = {"Content-type=application/xml"})
    @ResponseStatus(HttpStatus.GONE)
    @ResponseBody
    public String deleteStudentXML(@RequestBody Student student) {
        boolean result= Context.getInstance().getBean("studentService",StudentService.class).deleteStudent(student);
        if(!result){
            return "successful";
        }
        else return "error";
    }
}
