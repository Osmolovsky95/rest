package data.question;

import java.util.ArrayList;
import java.util.List;

public class BankQuestions {
     private List<Question> questions=new ArrayList<>();
     private static volatile BankQuestions instance;

    public static BankQuestions getInstance() {
        BankQuestions localInstance = instance;
        if (localInstance == null) {
            synchronized (BankQuestions.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new BankQuestions();
                }
            }
        }
        return localInstance;
    }
    public List<Question> getQuestions() {
        return questions;
    }
}
