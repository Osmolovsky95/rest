package test;

import data.question.BankQuestions;
import data.question.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test {

    public List<Question> generateNumberQuestion(List<Question> questions){
        List <Question> questions1=new ArrayList<>();
        List<Integer> range = IntStream.range(0, questions.size()-1).boxed().limit(11)
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(range);
        List<Question> questions2= BankQuestions.getInstance().getQuestions();
        for (Integer i:range){
            questions1.add(questions2.get(i));
        }
        return questions1;
    }
}
