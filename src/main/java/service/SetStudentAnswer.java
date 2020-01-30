package service;

import dao.spring.StudentSpringDAO;
import data.AssessmentSet;
import test.Context;

public class SetStudentAnswer {

    public boolean setAnswer(AssessmentSet assessmentSet){
        boolean result = Context.getInstance().getBean("studentSpringDAO", StudentSpringDAO.class).setStudentAnswer(assessmentSet);
        return result;
    }
}
