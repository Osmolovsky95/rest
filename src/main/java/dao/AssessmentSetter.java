package dao;

import data.student.Student;
import org.springframework.jdbc.core.JdbcTemplate;

public class AssessmentSetter {

    private JdbcTemplate jdbcTemplate;

    public AssessmentSetter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setToDB(Student student, double assessment) {
        student.getAssessments().add(assessment);
        String testName="TestName";
        long id=student.getId();
        String sql = "INSERT INTO studentsResult(testName,id_student,assessment) values (?,?,?)";
        jdbcTemplate.update(sql,testName,id,assessment);
    }
}
