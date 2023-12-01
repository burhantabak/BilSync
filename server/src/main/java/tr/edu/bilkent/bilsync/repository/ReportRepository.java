package tr.edu.bilkent.bilsync.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.Report;

public interface ReportRepository extends CrudRepository<Report,Long> {

    // we do not have any functions yet; to be reviewed by groupmates

    // for now our purpose is to inherit CrudRepo to do operations such as :

    /// save, findBy, delete
}
