package controllers;

import data.question.Question;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.QuestionService;
import test.Context;

@Controller
@RequestMapping(value = "/questions")
public class QuestionController {

    @RequestMapping(value = "/question",method = RequestMethod.POST,headers = {"Content-type=application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Question createQuestion(@RequestBody Question question) {
        return Context.getInstance().getBean("questionService", QuestionService.class).createQuestion(question);
    }

    @RequestMapping(value = "/question/{id}",method = RequestMethod.GET,headers = {"Content-type=application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Question getQuestionJSON(@PathVariable String id ) {
        return Context.getInstance().getBean("questionService", QuestionService.class).getQuestion(Long.parseLong(id));
    }
    @RequestMapping(value = "/question/{id}",method = RequestMethod.GET,headers = {"Content-type=application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Question getQuestionXML(@PathVariable String id ) {
        return Context.getInstance().getBean("questionService", QuestionService.class).getQuestion(Long.parseLong(id));
    }

    @RequestMapping(value = "/question/{id}",method = RequestMethod.DELETE,headers = {"Content-type=application/json"})
    @ResponseStatus(HttpStatus.GONE)
    @ResponseBody
    public String deleteQuestion(@PathVariable String id) {
        boolean result=Context.getInstance().getBean("questionService", QuestionService.class).deleteQuestion((Long.parseLong(id)));
        if(result){
            return "OK";
        }
        else {
            return "ERROR";
        }
    }
}
