package data.question;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Question  {

    private long id;
    private   String question;
    private List <String> answers=new ArrayList<>();
    private long trueNumber;
    private double assessment;
    private List <Integer> idAnswers=new ArrayList<>();

    public void setIdAnswers(List<Integer> idAnswers) {
        this.idAnswers = idAnswers;
    }

    public List<Integer> getIdAnswers() {
        return idAnswers;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Question(String question, long id) {
        this.id = id;
        this.question = question;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public Question() {
    }

    public Question(String question, List<String> answers, double assessment) {
        this.question = question;
        this.answers = answers;
        this.assessment = assessment;
    }

    public double getAssessment() {
        return assessment;
    }

    public void setAssessment(double assessment) {
        this.assessment = assessment;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public long getTrueNumber() {
        return trueNumber;
    }

    public void setTrueNumber(long trueNumber) {
        this.trueNumber = trueNumber;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answers=" + answers +
                ", trueNumber=" + trueNumber +
                ", assessment=" + assessment +
                ", idAnswers=" + idAnswers +
                '}';
    }
}
