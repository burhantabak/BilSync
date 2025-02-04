package tr.edu.bilkent.bilsync.entity;

import jakarta.persistence.*;

/**
 * The Report class represents a user-generated report on a particular entity, such as a user or a post.
 * It includes information about the reporter, the reported entity, the report type, and a description.
 * This class is mapped to a database table for persistent storage using JPA annotations.
 */
@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private long reporterId;

    @Column(nullable = false)
    private long reportedUserId;

    @Column(nullable = false)
    private long reportedEntityId;

    @Column
    private ReportType reportType = null;

    @Column
    private String description;

    /**
     * Default constructor for the Report class.
     */
    public Report() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getReporterId() {
        return reporterId;
    }

    public void setReporterId(long reporterId) {
        this.reporterId = reporterId;
    }

    public long getReportedUserId() {
        return reportedUserId;
    }

    public void setReportedUserId(long reportedUserId) {
        this.reportedUserId = reportedUserId;
    }

    public long getReportedEntityId() {
        return reportedEntityId;
    }

    public void setReportedEntityId(long reportedEntityId) {
        this.reportedEntityId = reportedEntityId;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}