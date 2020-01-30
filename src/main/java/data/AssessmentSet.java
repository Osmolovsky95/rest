package data;

import data.question.Question;
import data.student.Student;

public class AssessmentSet {
    private Student student;
    private Question question;
    private String answer;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "AssessmentSet{" +
                "student=" + student +
                ", question=" + question +
                ", answer='" + answer + '\'' +
                '}';
    }
}
