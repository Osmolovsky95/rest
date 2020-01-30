package dao.spring;

import data.question.Question;
import data.student.Student;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnswerSpringDAO  {

     private JdbcTemplate jdbcTemplate;

    public AnswerSpringDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    public void addAnswer(Question question, int trueNumber) {
        String insertSQL = "INSERT INTO answers (answer) Values (?) RETURNING id";
            List<Integer> idAnswers = new ArrayList<>();
            for (String answer : question.getAnswers()) {
              Map map =  jdbcTemplate.queryForMap(insertSQL,answer);
                int id=(int)map.get("id");
                idAnswers.add(id);
            }
             question.setIdAnswers(idAnswers);
             question.setTrueNumber(question.getIdAnswers().get(trueNumber-1));
             this.addTrueAnswer((question.getIdAnswers().get(trueNumber-1)),question.getId());
    }

    public void addTrueAnswer(long answer_id, long question_id)  {
        String insertSQL = "INSERT INTO trueAnswers (id_answer,id_question) Values (?,?)";
        jdbcTemplate.update(insertSQL,answer_id,question_id);
    }

    public  void addStudentAnswers(Question question, Student student, long currentAnswerId){
        String insertSQL="INSERT INTO studentAnswers (student_id,question_id,answer_id) VALUES(?,?,?)";
        jdbcTemplate.update(insertSQL,student.getId(),question.getId(),currentAnswerId);
    }

    public  JSONObject  selectStudentAnswers(String idQuestion) {
        JSONObject jsonObject=new JSONObject();
        long idd=Long.parseLong(idQuestion);
        String sqls = "SELECT * FROM questions WHERE id=?";
        Question question = new Question();

        String sql = "SELECT * FROM questions WHERE id=?";
        Map questions= jdbcTemplate.queryForMap(sqls,idd);
        question.setId((int)questions.get("id"));
        question.setQuestion((String) questions.get("question"));
        question.setAssessment((float)questions.get("assessment"));
        String sqlTrueAnswer = "SELECT id_answer FROM trueAnswers WHERE id_question=?";
        Map currentTrueAnswer=jdbcTemplate.queryForMap(sqlTrueAnswer,question.getId());
        question.setTrueNumber((int)currentTrueAnswer.get("id_answer"));

        String sqlAnswers = "SELECT * FROM question_answers WHERE question_id=?";
        String sqlAnswerText = "SELECT * FROM answers WHERE id=?";
        List<Map<String,Object>> list= jdbcTemplate.queryForList(sqlAnswers,question.getId());
        for(Map<String,Object> list1:list){
            int idAnswer = (int) list1.get("answer_id");
            question.getIdAnswers().add(idAnswer);
            Map answer= jdbcTemplate.queryForMap(sqlAnswerText,idAnswer);
            String answerContent =(String)answer.get("answer");
            question.getAnswers().add(answerContent);
        }
        Map jsonQuestion=new HashMap();
        List answers=new ArrayList();
        for (int i=0;i<question.getAnswers().size();i++){
            Map <String,String> answer=new HashMap<>();
            answer.put("id", question.getIdAnswers().get(i)+"");
            answer.put("text",question.getAnswers().get(i));
            answers.add(answer);
        }
        jsonQuestion.put("answers",answers);
        jsonQuestion.put("id",question.getId());
        jsonQuestion.put("text",question.getQuestion());
        jsonQuestion.put("goodAnswer",question.getTrueNumber());

        String join="SELECT name,student_id,answer_id FROM studentAnswers join questions on question_id=questions.id join" +
                " answers on answer_id=answers.id join students on student_id=students.id where studentAnswers.question_id=?";
        List<Map<String,Object>> studentss=new ArrayList<>();
        List<Map<String,Object>> students= jdbcTemplate.queryForList(join,question.getId());
       for (Map<String,Object> student:students){
          Map<String,Object> i=new HashMap<>();
          i.put("name",student.get("name"));
          i.put("id",student.get("student_id"));
          i.put("answer",student.get("answer_id"));
          studentss.add(i);
       }
       jsonObject.put("question",jsonQuestion);
       jsonObject.put("students",studentss);

       return jsonObject;
        }
    }

