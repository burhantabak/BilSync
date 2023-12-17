package tr.edu.bilkent.bilsync.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.UserEntity;

/**
 * Repository interface for managing UserEntity entities.
 * It extends CrudRepository for basic CRUD operations.
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    /**
     * Retrieves a UserEntity by email.
     *
     * @param email The email of the user.
     * @return The UserEntity associated with the email, or {@code null} if not found.
     */
    UserEntity findByEmail(String email);

    /**
     * Retrieves a UserEntity by ID.
     *
     * @param id The ID of the user.
     * @return The UserEntity associated with the ID, or {@code null} if not found.
     */
    UserEntity findById(long id);
}
