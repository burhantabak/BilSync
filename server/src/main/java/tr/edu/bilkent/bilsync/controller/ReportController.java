package tr.edu.bilkent.bilsync.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import tr.edu.bilkent.bilsync.entity.Comment;
import tr.edu.bilkent.bilsync.entity.Post;
import tr.edu.bilkent.bilsync.entity.Report;
import tr.edu.bilkent.bilsync.exception.NoRecordFoundException;
import tr.edu.bilkent.bilsync.exception.UserIsBannedException;
import tr.edu.bilkent.bilsync.service.CommentService;
import tr.edu.bilkent.bilsync.service.PostService;
import tr.edu.bilkent.bilsync.service.ReportService;

/**
 * REST controller for handling admin-related operations on reports.
 */
@RestController
@RequestMapping("/admin/reports")
public class ReportController {

    private final ReportService reportService;

    private final PostService postService;

    private final CommentService commentService;

    @Autowired
    public ReportController(ReportService reportService, PostService postService, CommentService commentService) {
        this.reportService = reportService;
        this.postService = postService;
        this.commentService = commentService;
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
    public ResponseEntity<?> viewReportedPost(@PathVariable Long reportId) {
        Post post = postService.getPostByID(reportId);
        if (post == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Post does not exist");
        } else {
            return ResponseEntity.ok(post);
        }

    }

    /**
     * Views details of a reported comment.
     *
     * @param reportId The ID of the report associated with the comment.
     * @return A ResponseEntity with a status code and a message.
     */
    @GetMapping("/{reportId}/viewComment")
    public ResponseEntity<?> viewReportedComment(@PathVariable Long reportId) {
        Comment comment = commentService.getCommentByID(reportId);
        if (comment == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Post does not exist");
        } else {
            return ResponseEntity.ok(comment);
        }
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
