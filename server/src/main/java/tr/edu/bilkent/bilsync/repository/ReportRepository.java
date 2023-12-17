package tr.edu.bilkent.bilsync.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.Report;
import tr.edu.bilkent.bilsync.entity.ReportType;

import java.util.List;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {

Report findByReporterIdAndReportedEntityIdAndReportType(long reporterId, long reportedEntityId, ReportType reportType);

}
