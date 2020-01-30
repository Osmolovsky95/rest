package service;

import dao.spring.AnswerSpringDAO;
import dao.spring.QuestionSpringDAO;
import data.question.BankQuestions;
import data.question.Question;
import test.Context;


public class QuestionService {

    public Question createQuestion(Question question){
        QuestionSpringDAO questionSpringDAO = Context.getInstance().getBean("questionSpringDAO", QuestionSpringDAO.class);
        questionSpringDAO.addQuestion(question);
        Context.getInstance().getBean("answerSpringDAO", AnswerSpringDAO.class).addAnswer(question,(int)question.getTrueNumber());
        for(long idAnswer:question.getIdAnswers()){
            questionSpringDAO.addQuestionAnswers(question.getId(),idAnswer);
        }
        BankQuestions.getInstance().getQuestions().add(question);
        return question;
    }

    public boolean deleteQuestion(long id){
       try{
           Context.getInstance().getBean("questionSpringDAO", QuestionSpringDAO.class).deleteQuestion(id);
           return true;
       }
       catch (Throwable e){
            e.printStackTrace();
           return false;
       }
    }

    public Question getQuestion(long id) {
        return Context.getInstance().getBean("questionSpringDAO", QuestionSpringDAO.class).getQuestion(id);
    }
}
