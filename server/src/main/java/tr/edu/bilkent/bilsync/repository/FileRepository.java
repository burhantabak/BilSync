package tr.edu.bilkent.bilsync.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.FileData;

/**
 * Repository interface for managing FileData entities.
 * It extends CrudRepository for basic CRUD operations.
 */
@Repository
public interface FileRepository extends CrudRepository<FileData, Integer> {

    /**
     * Retrieves a FileData entity by its name (case-insensitive).
     *
     * @param name The name of the FileData to retrieve.
     * @return The FileData entity with the specified name.
     */
    @Query("SELECT f FROM FileData f WHERE f.name = :name")
    FileData findByNameIgnoreCase(@Param("name") String name);
}
