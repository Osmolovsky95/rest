package config;

import dao.spring.AnswerSpringDAO;
import dao.spring.QuestionSpringDAO;
import dao.spring.StudentSpringDAO;
import data.question.Question;
import data.student.Student;
import generateReport.ExcelCreator;
import generateReport.IReportCreator;
import generateReport.WordCreator;
import generateReport.jasperReports.PDFCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import service.QuestionService;
import service.SetStudentAnswer;
import service.StudentService;

@Configuration
public class Config {

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(driverManagerDataSource());
    }
    @Bean
    public DriverManagerDataSource driverManagerDataSource() {
        DriverManagerDataSource driverManagerDataSource=new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/Testing");
        driverManagerDataSource.setUsername("postgres");
        driverManagerDataSource.setPassword("postgres");
        return driverManagerDataSource;
    }
    @Bean
    public AnswerSpringDAO answerSpringDAO(){
        return new AnswerSpringDAO(jdbcTemplate());
    }

    @Bean
    public QuestionSpringDAO questionSpringDAO(){
        return new QuestionSpringDAO(jdbcTemplate());
    }
    @Bean
    public StudentSpringDAO studentSpringDAO(){
        return new StudentSpringDAO(jdbcTemplate());
    }

    @Bean
    @Scope("prototype")
    public Student student(){
        return new Student();
    }
    @Bean
    @Scope("prototype")
    public Question question(){
        return new Question();
    }
    @Bean
    public StudentService studentService(){
        return new StudentService();
    }

    @Bean
    public QuestionService questionService(){
        return new QuestionService();
    }

    @Bean
    @Scope("prototype")
    public IReportCreator excelCreator(){
        return new ExcelCreator();
    }
    @Bean
    public IReportCreator wordCreator(){
        return new WordCreator();
    }

    @Bean
    public IReportCreator pdfCreator(){
        return new PDFCreator();
    }

    @Bean
    public SetStudentAnswer setStudentAnswer(){
        return new SetStudentAnswer();
    }
}

