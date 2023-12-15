package tr.edu.bilkent.bilsync.entity;

import jakarta.persistence.*;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private long reporterId;

    @Column(nullable = false)
    private long reportedId;

    @Column(nullable = false)
    private long reportedEntityId;

    private ReportType reportType;

    private String description;


}