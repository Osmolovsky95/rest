package dao;


import data.Administrator;
import data.student.Student;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.Map;

public class Registrator {

       private JdbcTemplate jdbcTemplate;
        String studentSQL="SELECT * FROM students WHERE name=?";
        String administratorSQL ="SELECT * FROM  administrators WHERE name=?";

    public Registrator(JdbcTemplate jdbcTemplate) throws SQLException {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Student registrationStudent(String name, String password){
       Student students=null;
       try{
            Map<String,Object>  student= jdbcTemplate.queryForMap(studentSQL,name);
            if(((String)student.get("name")).equals(name) & ((String)student.get("password")).equals(password)){
            students= new Student(name,password,(int)student.get("id"));
                return students;
            }
        }
        catch (EmptyResultDataAccessException e){
            students=null;
        }
       return students;
    }

    public Administrator registrationAdministrator(String name, String password){
        Administrator administrator=null;
        try{
            Map<String,Object>  admin= jdbcTemplate.queryForMap(administratorSQL,name);
            if(((String)admin.get("name")).equals(name) & ((String)admin.get("password")).equals(password)){
                administrator= new Administrator(name,password,(int)admin.get("id"));
                return administrator;
            }
        }
        catch (EmptyResultDataAccessException e){
            administrator=null;
        }
        return administrator;
    }
}
