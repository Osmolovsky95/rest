package controllers;

import data.AssessmentSet;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.SetStudentAnswer;
import test.Context;

@Controller
@RequestMapping(value = "/testing")
public class TestController {

    @RequestMapping(value = "/test/",method = RequestMethod.POST,headers = {"Content-type=application/json"})
    @ResponseStatus(HttpStatus.OK)
    public void testGoJSON(@RequestBody AssessmentSet assessmentSet){
        Context.getInstance().getBean("setStudentAnswer", SetStudentAnswer.class).setAnswer(assessmentSet);
    }

}
