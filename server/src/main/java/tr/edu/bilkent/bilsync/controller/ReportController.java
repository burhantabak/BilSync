package tr.edu.bilkent.bilsync.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import tr.edu.bilkent.bilsync.entity.Comment;
import tr.edu.bilkent.bilsync.entity.PostEntities.Post;
import tr.edu.bilkent.bilsync.entity.Report;
import tr.edu.bilkent.bilsync.entity.ReportType;
import tr.edu.bilkent.bilsync.entity.UserEntity;
import tr.edu.bilkent.bilsync.exception.NoRecordFoundException;
import tr.edu.bilkent.bilsync.exception.UserIsBannedException;
import tr.edu.bilkent.bilsync.service.AuthService;
import tr.edu.bilkent.bilsync.service.CommentService;
import tr.edu.bilkent.bilsync.service.PostServices.PostService;
import tr.edu.bilkent.bilsync.service.ReportService;
import tr.edu.bilkent.bilsync.service.UserInfoService;

/**
 * REST controller for handling admin-related operations on reports.
 */
@RestController
@RequestMapping("/admin/reports")
public class ReportController {

    private final ReportService reportService;

    private final PostService postService;

    private final CommentService commentService;

    private final UserInfoService userInfoService;

    /**
     * Constructor for ReportController, autowiring required services.
     *
     * @param reportService    The ReportService for handling report-related operations.
     * @param postService      The PostService for handling post-related operations.
     * @param commentService   The CommentService for handling comment-related operations.
     * @param userInfoService  The UserInfoService for handling user information.
     */
    @Autowired
    public ReportController(ReportService reportService, PostService postService, CommentService commentService, UserInfoService userInfoService) {
        this.reportService = reportService;
        this.postService = postService;
        this.commentService = commentService;
        this.userInfoService = userInfoService;
    }

    /**
     * Creates a report for a post.
     *
     * @param report The Report object containing details of the report.
     * @return A ResponseEntity with a status code and a message.
     */
    @PostMapping("/createPostReport")
    public ResponseEntity<?> createPostReport(@RequestBody Report report) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();
        if(user == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User cannot be found");
        report.setReportType(ReportType.POST_REPORT);
        report.setReporterId(user.getId());
        if(postService.getPostByID(report.getReportedEntityId()) == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Post could not be found");
        return uploadValidReport(report, user);
    }

    /**
     * Creates a report for a comment.
     *
     * @param report The Report object containing details of the report.
     * @return A ResponseEntity with a status code and a message.
     */
    @PostMapping("/createCommentReport")
    public ResponseEntity<?> createCommentReport(@RequestBody Report report) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();
        if(user == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User cannot be found");
        report.setReportType(ReportType.COMMENT_REPORT);
        report.setReporterId(user.getId());
        if(commentService.getCommentByID(report.getReportedEntityId()) == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Comment could not be found");
        return uploadValidReport(report, user);
    }

    /**
     * Validates and uploads a report to the database.
     *
     * @param report The Report object to be validated and uploaded.
     * @return A ResponseEntity with a status code and a message.
     */
    private ResponseEntity<?> uploadValidReport(Report report, UserEntity user) {
        if(user.getId() == report.getReportedUserId())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You cannot report yourself");
        if(report.getDescription().length() < 10)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Report description must be longer than 10 characters");
        if(report.getDescription().length() > 1000)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Report description must be shorter than 1000 characters");
        if(userInfoService.loadUserById(report.getReportedUserId()) == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Reported user cannot be found");
        if(!reportService.createReport(report))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Report could not be saved");
        return ResponseEntity.ok().build();
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
