package controllers;

import data.question.Question;
import data.student.Student;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.StudentService;
import test.Context;

@Controller
@RequestMapping(value = "/testing")
public class Test {

    @RequestMapping(value = "/test/",method = RequestMethod.POST,headers = {"Content-type=application/json"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudentJSON(@RequestBody Student student,Question question, String answer){
        System.out.println(student);
        System.out.println(question);
        System.out.println(answer);
    }

}
