package tr.edu.bilkent.bilsync.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.Report;
import tr.edu.bilkent.bilsync.entity.ReportType;
import tr.edu.bilkent.bilsync.exception.NoRecordFoundException;
import tr.edu.bilkent.bilsync.repository.ReportRepository;

import java.util.Optional;


/**
 * Service class for managing reports and related operations.
 */
@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserInfoService userInfoService;

    /**
     * Constructs a new ReportService with the specified dependencies.
     *
     * @param reportRepository The repository for managing Report entities.
     * @param userInfoService  The service for managing user information.
     */
    @Autowired
    public ReportService(ReportRepository reportRepository, UserInfoService userInfoService) {
        this.reportRepository = reportRepository;
        this.userInfoService = userInfoService;
    }

    /**
     * Bans the user who is reported in a report.
     *
     * @param reportedId The ID of the user being reported.
     */
    public void banReportedUser(Long reportedId) {
        userInfoService.banUser(reportedId);
    }

    /**
     * Deletes a report with the specified ID.
     *
     * @param reportId The ID of the report to be deleted.
     * @throws EntityNotFoundException if the report with the specified ID is not found.
     */
    public void deleteReport(Long reportId) {
        Optional<Report> report = reportRepository.findById(reportId);
        if (report.isEmpty()) {
            throw new EntityNotFoundException("Report with ID " + reportId + " not found");
        } else {
            reportRepository.deleteById(reportId);
        }
    }

    /**
     * Retrieves all reports.
     *
     * @return An iterable containing all reports.
     * @throws NoRecordFoundException if no records are found in the database for Report entities.
     */
    public Iterable<Report> getAllReports() {
        Iterable<Report> reports = reportRepository.findAll();
        if (!reports.iterator().hasNext()) {
            throw new NoRecordFoundException("No records found in the database for YourEntity");
        }
        return reports;
    }

    /**
     * Creates a report and saves it in the repository.
     *
     * @param report The Report object to be created and saved.
     * @return true if the report is successfully created and saved, false otherwise.
     */
    public boolean createReport(Report report) {
        try{
            reportRepository.save(report);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    /**
     * Retrieves a previous report based on reporter ID, reported entity ID, and report type.
     *
     * @param reporterId        The ID of the user who reported.
     * @param reportedEntityId The ID of the reported entity.
     * @param reportType        The type of the report.
     * @return The previous report with the specified criteria.
     */
    public Report getPreviousReport(long reporterId, long reportedEntityId, ReportType reportType)
    {
        return  reportRepository.findByReporterIdAndReportedEntityIdAndReportType( reporterId,  reportedEntityId,  reportType);
    }
}
