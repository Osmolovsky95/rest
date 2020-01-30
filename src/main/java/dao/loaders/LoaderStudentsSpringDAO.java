package dao.loaders;

import data.student.GroupStudents;
import data.student.Student;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class LoaderStudentsSpringDAO {
    private JdbcTemplate jdbcTemplate;

    public LoaderStudentsSpringDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void load() {
        String selectSQL = "SELECT * FROM students";
        List<Map<String, Object>> students = jdbcTemplate.queryForList(selectSQL);
        for (Map<String, Object> student : students) {
            long id = (int) student.get("id");
            String name = (String) student.get("name");
            String password = (String) student.get("password");
            Student current = new Student(name, password, id);
            GroupStudents.getInstance().getStudents().add(current);
        }
    }
}
