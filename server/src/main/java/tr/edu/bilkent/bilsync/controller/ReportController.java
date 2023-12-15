package tr.edu.bilkent.bilsync.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import tr.edu.bilkent.bilsync.entity.Report;
import tr.edu.bilkent.bilsync.exception.NoRecordFoundException;
import tr.edu.bilkent.bilsync.exception.UserIsBannedException;
import tr.edu.bilkent.bilsync.service.ReportService;

/**
 * REST controller for handling admin-related operations on reports.
 */
@RestController
@RequestMapping("/admin/reports")
public class ReportController {

    private final ReportService reportService;


    /**
     * Constructs a new ReportController with the specified service dependency.
     *
     * @param reportService The service responsible for report-related operations.
     */
    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;

    }

    /**
     * Retrieves all reports.
     *
     * @return A ResponseEntity containing an iterable of reports.
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllReports() {
        try {
            Iterable<Report> reports = reportService.getAllReports();
            return ResponseEntity.ok(reports);
        } catch (NoRecordFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No records found in the database for YourEntity");
        }
    }

    /**
     * Deletes a report with the specified ID.
     *
     * @param reportId The ID of the report to be deleted.
     * @return A ResponseEntity with a status code and a message.
     */
    @DeleteMapping("/delete/{reportId}")
    public ResponseEntity<String> deleteReport(@PathVariable Long reportId) {
        try {
            reportService.deleteReport(reportId);
            return ResponseEntity.status(HttpStatus.OK).body("Report deleted successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Report does not exist");
        }
    }

    /**
     * Views details of a reported post.
     *
     * @param reportId The ID of the report associated with the post.
     * @return A ResponseEntity with a status code and a message.
     */
    @GetMapping("/{reportId}/viewPost")
    public ResponseEntity<String> viewReportedPost(@PathVariable Long reportId) {
        // Implement logic to view details of a report
        return new ResponseEntity<>("Report details", HttpStatus.OK);
    }

    /**
     * Views details of a reported comment.
     *
     * @param reportId The ID of the report associated with the comment.
     * @return A ResponseEntity with a status code and a message.
     */
    @GetMapping("/{reportId}/viewComment")
    public ResponseEntity<String> viewReportedComment(@PathVariable Long reportId) {
        // Implement logic to view details of a report
        return new ResponseEntity<>("Report details", HttpStatus.OK);
    }

    /**
     * Bans the user reported in a specific report.
     *
     * @param reportId The ID of the report associated with the user to be banned.
     * @return A ResponseEntity with a status code and a message.
     */
    @PostMapping("/{reportId}/ban")
    public ResponseEntity<?> banReportedUser(@PathVariable Long reportId) {

        try {
            reportService.banReportedUser(reportId);
            return ResponseEntity.status(HttpStatus.OK).body("User banned successfully");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (UserIsBannedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is already banned");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}
