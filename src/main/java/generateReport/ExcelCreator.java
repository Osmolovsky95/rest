package generateReport;

import generateReport.jasperReports.ReportBeanQuestion;
import generateReport.jasperReports.UtilReport;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.PropertyTemplate;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;


public class ExcelCreator implements IReportCreator {

    public void createReport(String s)  {
        ReportBeanQuestion reportBeanQuestion = new ReportBeanCreator().parse(s);
        XSSFWorkbook workbook=new XSSFWorkbook();
        File file = new File("C:\\Users\\A.Asmalouski\\IdeaProjects\\project\\TestingRest\\reports\\question.xlsx");

        try (OutputStream fileOut = new FileOutputStream(file)) {
            XSSFSheet sheet1= workbook.createSheet("Question № " + reportBeanQuestion.getId());

            Row question=sheet1.createRow(1);
            Cell qst=question.createCell(0);
            sheet1.addMergedRegion(new CellRangeAddress(1,1,0,2));
            CellUtil.setAlignment(qst,HorizontalAlignment.CENTER);
            qst.setCellValue("Вопрос");

            sheet1.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);

            Row headTableQuestion=sheet1.createRow(2);
            Cell idQuestion=headTableQuestion.createCell(0);
            idQuestion.setCellValue("id");
            CellUtil.setAlignment(idQuestion,HorizontalAlignment.CENTER);
            Cell text=headTableQuestion.createCell(1);
            text.setCellValue("text");
            sheet1.setColumnWidth(1,15000);
            CellUtil.setAlignment(text,HorizontalAlignment.CENTER);
            text.getCellStyle().setWrapText(true);

                              ////////Question

            sheet1.setColumnWidth(2,5000);
            Cell goodAnswer=headTableQuestion.createCell(2);
            goodAnswer.setCellValue("goodAnswer");
            CellUtil.setAlignment(goodAnswer,HorizontalAlignment.CENTER);
            goodAnswer.getCellStyle().setWrapText(true);

            Row rowData=sheet1.createRow(3);
            Cell idQstt=rowData.createCell(0);
            CellUtil.setAlignment(idQstt,HorizontalAlignment.CENTER);
            idQstt.setCellValue(reportBeanQuestion.getId());
            Cell textQst=rowData.createCell(1);
            CellUtil.setAlignment(textQst,HorizontalAlignment.CENTER);
            textQst.setCellValue(reportBeanQuestion.getText());
            Cell goodAnswerQst=rowData.createCell(2);
            goodAnswerQst.setCellValue(reportBeanQuestion.getGoodAnswer());
            CellUtil.setAlignment(goodAnswerQst,HorizontalAlignment.CENTER);

            PropertyTemplate pt =  new  PropertyTemplate ();
            pt.drawBorders ( new  CellRangeAddress ( 1,  3,  0 ,  2 ),BorderStyle.MEDIUM, BorderExtent.OUTSIDE);
            pt.applyBorders(sheet1);

            /////////////////Answers//////////////////////////////////////////
            Row stringAnswer=sheet1.createRow(4);
            Cell cell= stringAnswer.createCell(0);
            sheet1.addMergedRegion(new CellRangeAddress(4,4,0,2));
            CellUtil.setAlignment(cell,HorizontalAlignment.CENTER);
            cell.setCellValue("Ответы");


           Row headAnswer=sheet1.createRow(5);
           Cell idAnswer=headAnswer.createCell(0);
           idAnswer.setCellValue("id");
           CellUtil.setAlignment(idAnswer,HorizontalAlignment.CENTER);
           Cell textAnswer=headAnswer.createCell(1);
           CellUtil.setAlignment(textAnswer,HorizontalAlignment.CENTER);
           textAnswer.setCellValue("text");
           Cell quantity=headAnswer.createCell(2);
           CellUtil.setAlignment(textAnswer,HorizontalAlignment.CENTER);
           quantity.setCellValue("Количество");


             int lastRow=headAnswer.getRowNum();
             int currentRow=0;
               for (Map<String,Object> answer:reportBeanQuestion.getAnswers()){
                   Map<Long,Integer> studentAnswer= UtilReport.quantityStudentAnswer(reportBeanQuestion.getStudents(),reportBeanQuestion.getAnswers());

                  if(currentRow==0) {
                      Row row = sheet1.createRow(lastRow+1);
                      Cell id = row.createCell(0);
                      Cell textAns = row.createCell(1);
                      Cell quantityAns = row.createCell(2);
                      long idAnswers=Long.parseLong((String) answer.get("id"));
                      id.setCellValue(idAnswers);
                      textAns.setCellValue((String) answer.get("text"));
                      textAns.getCellStyle().setWrapText(true);
                      quantityAns.setCellValue(studentAnswer.get(idAnswers));
                      CellUtil.setAlignment(id,HorizontalAlignment.CENTER);
                      CellUtil.setAlignment(textAns,HorizontalAlignment.CENTER);
                      currentRow=row.getRowNum();
                  }
                  else
                  {
                      Row row = sheet1.createRow(currentRow+1);
                      Cell id = row.createCell(0);
                      Cell textAns = row.createCell(1);
                      Cell quantityAns = row.createCell(2);
                      long idAnswers=Long.parseLong((String) answer.get("id"));
                      id.setCellValue(idAnswers);
                      id.setCellValue(Long.parseLong((String) answer.get("id")));
                      textAns.setCellValue((String) answer.get("text"));
                      textAns.getCellStyle().setWrapText(true);
                      quantityAns.setCellValue(studentAnswer.get(idAnswers));
                      CellUtil.setAlignment(id,HorizontalAlignment.CENTER);
                      CellUtil.setAlignment(textAns,HorizontalAlignment.CENTER);
                      currentRow=row.getRowNum();
                  }
           }
            pt.drawBorders ( new  CellRangeAddress ( stringAnswer.getRowNum(),  currentRow,  0 ,  2 ),BorderStyle.MEDIUM, BorderExtent.ALL);
            pt.applyBorders(sheet1);

            /////////////////////Students/////////////////////////////////////////////////
            Row stringStudents=sheet1.createRow(currentRow+1);
            Cell students= stringStudents.createCell(0);
            sheet1.addMergedRegion(new CellRangeAddress(currentRow+1,currentRow+1,0,2));
            CellUtil.setAlignment(students,HorizontalAlignment.CENTER);
            students.setCellValue("Студенты");


            Row headStudents=sheet1.createRow(stringStudents.getRowNum()+1);
            Cell idStudent=headStudents.createCell(0);
            Cell nameStudent=headStudents.createCell(1);
            Cell answerStudents=headStudents.createCell(2);
            idStudent.setCellValue("id");
            CellUtil.setAlignment(idStudent,HorizontalAlignment.CENTER);
            nameStudent.setCellValue("name");
            CellUtil.setAlignment(nameStudent,HorizontalAlignment.CENTER);
            answerStudents.setCellValue("answer");
            CellUtil.setAlignment(answerStudents,HorizontalAlignment.CENTER);


            int lastRowStudent=headStudents.getRowNum();
            int currentRowStudent=0;
            for (Map<String,Object> student:reportBeanQuestion.getStudents()){
                if(currentRowStudent==0) {
                    Row row = sheet1.createRow(lastRowStudent+1);
                    Cell id = row.createCell(0);
                    Cell name = row.createCell(1);
                    Cell answer = row.createCell(2);
                    long idStudents=Long.parseLong((String) student.get("id"));
                    id.setCellValue(idStudents);
                    name.setCellValue((String) student.get("name"));
                    name.getCellStyle().setWrapText(true);
                    answer.setCellValue(Long.parseLong((String) student.get("answer")));
                    CellUtil.setAlignment(id,HorizontalAlignment.CENTER);
                    CellUtil.setAlignment(name,HorizontalAlignment.CENTER);
                    CellUtil.setAlignment(answer,HorizontalAlignment.CENTER);
                    currentRowStudent=row.getRowNum();
                }
                else
                {
                    Row row = sheet1.createRow(currentRowStudent+1);
                    Cell id = row.createCell(0);
                    Cell name = row.createCell(1);
                    Cell answer = row.createCell(2);
                    long idStudents=Long.parseLong((String) student.get("id"));
                    id.setCellValue(idStudents);
                    name.setCellValue((String) student.get("name"));
                    name.getCellStyle().setWrapText(true);
                    answer.setCellValue(Long.parseLong((String) student.get("answer")));
                    CellUtil.setAlignment(id,HorizontalAlignment.CENTER);
                    CellUtil.setAlignment(name,HorizontalAlignment.CENTER);
                    CellUtil.setAlignment(answer,HorizontalAlignment.CENTER);
                    currentRowStudent=row.getRowNum();
                }
            }

            pt.drawBorders ( new  CellRangeAddress ( stringStudents.getRowNum(),  currentRowStudent,  0 ,  2 ),BorderStyle.MEDIUM, BorderExtent.ALL);
            pt.applyBorders(sheet1);

            Header header = sheet1.getHeader();
            header.setCenter("Отчет по вопросу №  " + reportBeanQuestion.getId());
            workbook.write (fileOut);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}



