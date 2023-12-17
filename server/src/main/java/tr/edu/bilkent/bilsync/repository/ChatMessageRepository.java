package tr.edu.bilkent.bilsync.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.ChatMessage;

/**
 * Repository interface for managing ChatMessage entities.
 * It extends CrudRepository for basic CRUD operations.
 */
@Repository
public interface ChatMessageRepository extends CrudRepository<ChatMessage, Long> { }
