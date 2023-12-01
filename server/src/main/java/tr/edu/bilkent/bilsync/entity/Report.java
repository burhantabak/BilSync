package tr.edu.bilkent.bilsync.entity;

import jakarta.persistence.*;

public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long commentId;

    @Column(nullable = false)
    private Long reporterId;

    @Column(length = 255, nullable = false)
    private String reason;

    // create constructors for further usage in the coding process
    public Report() {}

    public Report(Long commentId, Long reporterId, String reason) {
        this.commentId = commentId;
        this.reporterId = reporterId;
        this.reason = reason;
    }

    // getter and setter methods for Report
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getReporterId() {
        return reporterId;
    }

    public void setReporterId(Long reporterId) {
        this.reporterId = reporterId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
