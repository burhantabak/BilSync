package tr.edu.bilkent.bilsync.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.Report;
import tr.edu.bilkent.bilsync.entity.ReportType;

import java.util.List;

/**
 * Repository interface for managing Report entities.
 * It extends CrudRepository for basic CRUD operations.
 */
@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {

    /**
     * Retrieves a report by the specified reporter ID, reported entity ID, and report type.
     *
     * @param reporterId       The ID of the user reporting the entity.
     * @param reportedEntityId The ID of the reported entity.
     * @param reportType       The type of the report (e.g., COMMENT_REPORT or POST_REPORT).
     * @return The report with the specified parameters, or null if not found.
     */
    Report findByReporterIdAndReportedEntityIdAndReportType(long reporterId, long reportedEntityId, ReportType reportType);
}
