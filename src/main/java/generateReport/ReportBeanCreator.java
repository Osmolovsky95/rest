package generateReport;

import dao.spring.AnswerSpringDAO;

import generateReport.jasperReports.ReportBeanQuestion;
import org.json.JSONArray;
import org.json.JSONObject;
import test.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportBeanCreator {

    public ReportBeanQuestion parse(String s){
        ReportBeanQuestion reportBeanQuestion=new ReportBeanQuestion();
        JSONObject jsonObject= Context.getInstance().getBean("answerSpringDAO", AnswerSpringDAO.class).selectStudentAnswers(s);

        JSONObject jsonObject1=jsonObject.getJSONObject("question");
        String text=jsonObject1.getString("text");
        reportBeanQuestion.setText(text);
        long id=jsonObject1.getLong("id");
        reportBeanQuestion.setId(id);
        long goodAnswer=jsonObject1.getLong("goodAnswer");
        reportBeanQuestion.setGoodAnswer(goodAnswer);

        JSONArray jsonAnswers=jsonObject1.getJSONArray("answers");
        JSONArray jsonStudents=jsonObject.getJSONArray("students");
        List<Map<String,Object>>answers=new ArrayList<>();
        List<Map<String,Object>> students=new ArrayList<>();

        for (int i=0;i<jsonAnswers.length();i++){
            JSONObject jsonObject2= jsonAnswers.getJSONObject(i);
            Map<String,Object> answer=new HashMap();
            answer.put("id",jsonObject2.get("id"));
            answer.put("text",jsonObject2.getString("text"));
            answers.add(answer);
        }

        for (int i=0;i<jsonStudents.length();i++){
            JSONObject jsonObject2= jsonStudents.getJSONObject(i);
            Map<String,Object> student=new HashMap();
            student.put("id",jsonObject2.get("id"));
            student.put("name",jsonObject2.getString("name"));
            student.put("answer",jsonObject2.getString("answer"));
            students.add(student);
        }
        reportBeanQuestion.setAnswers(answers);
        reportBeanQuestion.setStudents(students);
        return reportBeanQuestion;
    }
}
