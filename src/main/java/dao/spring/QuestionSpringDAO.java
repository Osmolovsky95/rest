package dao.spring;


import data.question.BankQuestions;
import data.question.Question;
import org.springframework.jdbc.core.JdbcTemplate;
import test.Context;

import java.util.List;
import java.util.Map;

public class QuestionSpringDAO {
   private JdbcTemplate jdbcTemplate;

    public QuestionSpringDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addQuestion(Question question)  {
        String insertQuestion="INSERT INTO questions (question,assessment) Values (?,?) RETURNING id";
           Map map= jdbcTemplate.queryForMap(insertQuestion,question.getQuestion(),question.getAssessment());
           question.setId((int)map.get("id"));
           BankQuestions.getInstance().getQuestions().add(question);
    }

    public void addQuestionAnswers(long question_id,long answer_id)  {
        String insertQuestion="INSERT INTO question_answers  (question_id,answer_id) Values (?,?)";
        jdbcTemplate.update(insertQuestion,question_id,answer_id);
    }

    public void deleteQuestion (long id){
        String sql1="delete  from trueAnswers where id_question=?";
        String sql2="delete from question_answers where question_id=?";
        String sql3="delete from studentanswers where question_id=?";
        String sql4="delete  from questions where id=?";
        jdbcTemplate.update(sql1,id);
        jdbcTemplate.update(sql2,id);
        jdbcTemplate.update(sql3,id);
        jdbcTemplate.update(sql4,id);
    }

    public Question getQuestion(long ids) {
        String sql = "SELECT * FROM questions where id=?";
        List<Map<String, Object>> questions = jdbcTemplate.queryForList(sql,ids);
        Question question = Context.getInstance().getBean("question", Question.class);
        for (Map<String, Object> qst : questions) {
            long id = (int) qst.get("id");
            String questionText = (String) qst.get("question");
            double assessment = (float) qst.get("assessment");
            question.setAssessment(assessment);
            question.setQuestion(questionText);
            question.setId(id);
            String sqlTrueAnswer = "SELECT id_answer FROM trueAnswers WHERE id_question=?";
            Map<String, Object> trueAnswer = jdbcTemplate.queryForMap(sqlTrueAnswer, id);
            question.setTrueNumber((int) trueAnswer.get("id_answer"));
            String sqlAnswerText = "SELECT * FROM answers WHERE id=?";
            String sqlAnswers = "SELECT * FROM question_answers WHERE question_id=?";
            List<Map<String, Object>> answers = jdbcTemplate.queryForList(sqlAnswers, question.getId());
            for (Map<String, Object> answer : answers) {
                int idAnswer = (int) answer.get("answer_id");
                question.getIdAnswers().add(idAnswer);
                Map<String, Object> text = jdbcTemplate.queryForMap(sqlAnswerText, idAnswer);
                question.getAnswers().add((String) text.get("answer"));
            }
        }
        return question;
    }
}
