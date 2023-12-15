package tr.edu.bilkent.bilsync.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import tr.edu.bilkent.bilsync.entity.Report;
import tr.edu.bilkent.bilsync.exception.UserIsBannedException;
import tr.edu.bilkent.bilsync.service.ReportService;
import tr.edu.bilkent.bilsync.service.UserInfoService;

import java.util.List;

@RestController
@RequestMapping("/admin/reports")
public class ReportController {

    private final ReportService reportService;


    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;

    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Report>> getAllReports() {
        Iterable<Report> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    @DeleteMapping("/delete/{reportId}")
    public ResponseEntity<String> deleteReport(@PathVariable Long reportId) {
        try {
            reportService.deleteReport(reportId);
            return ResponseEntity.status(HttpStatus.OK).body("Report deleted successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Report does not exist");
        }
    }

    @GetMapping("/{reportId}/viewPost")
    public ResponseEntity<String> viewReportedPost(@PathVariable Long reportId) {
        // Implement logic to view details of a report
        return new ResponseEntity<>("Report details", HttpStatus.OK);
    }

    @GetMapping("/{reportId}/viewComment")
    public ResponseEntity<String> viewReportedComment(@PathVariable Long reportId) {
        // Implement logic to view details of a report
        return new ResponseEntity<>("Report details", HttpStatus.OK);
    }

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
