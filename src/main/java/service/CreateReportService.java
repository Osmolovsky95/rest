package service;

import generateReport.IReportCreator;

public class CreateReportService {

    public CreateReportService(IReportCreator iReportCreator,String id) {
        iReportCreator.createReport(id);
    }
}
