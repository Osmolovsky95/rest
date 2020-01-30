package dao.loaders;

import data.question.BankQuestions;
import data.question.Question;
import org.springframework.jdbc.core.JdbcTemplate;
import test.Context;
import java.util.List;
import java.util.Map;

public class LoaderQuestionsSpringDAO {
   private JdbcTemplate jdbcTemplate;

    public LoaderQuestionsSpringDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void load() {

            String sql = "SELECT * FROM questions";
            List<Map<String,Object>> questions = jdbcTemplate.queryForList(sql);
            for (Map<String,Object> qst:questions){

              Question question= Context.getInstance().getBean("question",Question.class);
              long id = (int)qst.get("id");
              String questionText = (String) qst.get("question");
              double assessment = (float)qst.get("assessment");
              question.setAssessment(assessment);
              question.setQuestion(questionText);
              question.setId(id);

              String sqlTrueAnswer = "SELECT id_answer FROM trueAnswers WHERE id_question=?";
              Map<String,Object> trueAnswer=  jdbcTemplate.queryForMap(sqlTrueAnswer,id);
              question.setTrueNumber((int)trueAnswer.get("id_answer"));
              String sqlAnswerText = "SELECT * FROM answers WHERE id=?";
              String sqlAnswers = "SELECT * FROM question_answers WHERE question_id=?";
              List<Map<String,Object>> answers=jdbcTemplate.queryForList(sqlAnswers,question.getId());
              for (Map <String,Object> answer:answers) {
                  int idAnswer =(int)answer.get("answer_id");
                  question.getIdAnswers().add(idAnswer);
                  Map <String,Object> text= jdbcTemplate.queryForMap(sqlAnswerText,idAnswer);
                  question.getAnswers().add((String) text.get("answer"));
              }
              BankQuestions.getInstance().getQuestions().add(question);
            }
    }
}
