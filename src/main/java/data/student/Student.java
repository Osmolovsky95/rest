package data.student;




import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement
public class Student  {
    private String name;
    private long id;
    private  List<Double> assessments=new ArrayList<>();
    private String password;
    private double resultAssessment=this.getResultAssessment();

    public void setResultAssessment(double resultAssessment) {
        this.resultAssessment = resultAssessment;
    }

    public Student(String name, String password, long id) {
        this.name = name;
        this.id = id;
        this.password = password;
    }

    public Student(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student() {
    }

    public double getResultAssessment() {
        double resultAssessment=0;

        for (double assessment:assessments){
            resultAssessment+=assessment;
        }
        if(resultAssessment==0){
            return 0.0;
        }
        else {
            return (resultAssessment / assessments.size());
        }
    }

    public void setAssessments(List<Double> assessments) {
        this.assessments = assessments;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Double> getAssessments() {
        return assessments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", assessments=" + assessments +
                ", password='" + password + '\'' +
                ", resultAssessment=" + resultAssessment +
                '}';
    }

    public long getId() {
        return id;
    }
}
