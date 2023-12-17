package tr.edu.bilkent.bilsync.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.Image;

/**
 * Repository interface for managing Image entities.
 * It extends CrudRepository for basic CRUD operations.
 */
@Repository
public interface ImageRepository extends CrudRepository<Image, Long> { }
