package generateReport.jasperReports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilReport {

    public static Map<Long,Integer> quantityStudentAnswer(List<Map<String,Object>> students, List<Map<String,Object>> answers){
        Map<Long,Integer> quantityStudents=new HashMap<>();
        for (Map<String,Object> answer:answers){
            int count=0;
            long id=Long.parseLong((String) answer.get("id"));
            for (Map<String,Object> student:students){
               long currentAnswer=Long.parseLong((String) student.get("answer"));
               if(id==currentAnswer){
                   count++;
               }
            }
            quantityStudents.put(id,count);
        }
        return quantityStudents;
    }
}
