package tr.edu.bilkent.bilsync.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.Report;
import tr.edu.bilkent.bilsync.repository.ReportRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    private final ReportRepository reportRepository ;
    private final UserInfoService userInfoService;

    @Autowired
    public ReportService(ReportRepository reportRepository,UserInfoService userInfoService) {
        this.reportRepository=reportRepository;
        this.userInfoService = userInfoService;
    }

    public void banReportedUser(Long reportedId){
       userInfoService.banUser(reportedId);
    }

    public void deleteReport(Long reportId) {
        Optional<Report> report=reportRepository.findById(reportId);
        if (report.isEmpty()) {
            throw new EntityNotFoundException("Report with ID " + reportId + " not found");
        }
        else{
            reportRepository.deleteById(reportId);
        }
    }

    public Iterable<Report> getAllReports() {
        return reportRepository.findAll();
    }


    // Methods for handling report-related logic, such as creating, deleting, and fetching reports
}
