package generateReport.jasperReports;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportBeanQuestion {
    private long goodAnswer;
    private String text;
    private long id;
    private  List<Map<String,Object>> answers= new ArrayList<>();
    private  List<Map<String,Object>> students=new ArrayList<>();
    private  Map<String,Integer> countAnswer;

    public long getGoodAnswer() {
        return goodAnswer;
    }

    public void setGoodAnswer(long goodAnswer) {
        this.goodAnswer = goodAnswer;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Map<String, Object>> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Map<String, Object>> answers) {
        this.answers = answers;
    }

    public List<Map<String, Object>> getStudents() {
        return students;
    }

    public void setStudents(List<Map<String, Object>> students) {
        this.students = students;
    }

    public void setCountAnswer(Map<String, Integer> countAnswer) {
        this.countAnswer = countAnswer;
    }

    @Override
    public String toString() {
        return "ReportBeanQuestion{" +
                "goodAnswer=" + goodAnswer +
                ", text='" + text + '\'' +
                ", id=" + id +
                ", answers=" + answers +
                ", students=" + students +
                ", countAnswer=" + countAnswer +
                '}';
    }
}
