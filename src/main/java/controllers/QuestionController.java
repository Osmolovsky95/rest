package controllers;

import data.question.Question;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.QuestionService;

@Controller
public class QuestionController {
    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @RequestMapping(value = "/question",method = RequestMethod.POST,headers = {"Content-type=application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Question createQuestion(@RequestBody Question question) {
      question =  questionService.createQuestion(question);
      return question;
    }

    @RequestMapping(value = "/question/{id}",method = RequestMethod.GET,headers = {"Content-type=application/json"})
    @ResponseBody
    public Question getQuestionJSON(@PathVariable String id ) {
        return questionService.getQuestion(Long.parseLong(id));
    }
    @RequestMapping(value = "/question/{id}",method = RequestMethod.GET,headers = {"Content-type=application/xml"})
    @ResponseBody
    public Question getQuestionXML(@PathVariable String id ) {
        return questionService.getQuestion(Long.parseLong(id));
    }

    @RequestMapping(value = "/question/{id}",method = RequestMethod.DELETE,headers = {"Content-type=application/json"})
    @ResponseBody
    public String deleteQuestion(@PathVariable String id) {
        boolean result=questionService.deleteQuestion((Long.parseLong(id)));
        if(result){
            return "OK";
        }
        else {
            return "ERROR";
        }
    }
}
