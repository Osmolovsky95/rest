package controllers;

import generateReport.ExcelCreator;
import generateReport.IReportCreator;
import generateReport.WordCreator;
import generateReport.jasperReports.PDFCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.CreateReportService;
import test.Context;

@Controller
@RequestMapping(value = "/report")
public class ReportController {


    @RequestMapping(value = "/word/{idQuestion}",method = RequestMethod.GET)
    public void  generateWordReport(@PathVariable String idQuestion){
        IReportCreator iReportCreator= Context.getInstance().getBean("wordCreator",WordCreator.class);
        new CreateReportService(iReportCreator,idQuestion);
    }
 
    @RequestMapping(value = "/excel/{idQuestion}",method = RequestMethod.GET)
    public void generateExcelReport(@PathVariable String idQuestion){
        IReportCreator iReportCreator= Context.getInstance().getBean("excelCreator",ExcelCreator.class);
        new CreateReportService(iReportCreator,idQuestion);
    }

    @RequestMapping(value = "/pdf/{idQuestion}",method = RequestMethod.GET)
    public void generatePDFReport(@PathVariable String idQuestion){
        IReportCreator iReportCreator= Context.getInstance().getBean("pdfCreator",PDFCreator.class);
        new CreateReportService(iReportCreator,idQuestion);
    }
}
