package tr.edu.bilkent.bilsync.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.FileData;

@Repository
public interface FileRepository extends CrudRepository<FileData, Integer> {
    @Query("SELECT f FROM FileData f WHERE f.name = :name")
    FileData findByNameIgnoreCase(@Param("name") String name);
}
