package tr.edu.bilkent.bilsync.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.Chat;
import tr.edu.bilkent.bilsync.entity.UserEntity;

import java.util.List;

/**
 * Repository interface for managing ChatMessage entities.
 * It extends CrudRepository for basic CRUD operations.
 */
@Repository
public interface ChatRepository extends CrudRepository<Chat, Long> {

    /**
     * Custom query to find chats by users containing the specified user.
     *
     * @param user The user to find chats for.
     * @return List of chats containing the specified user.
     */
    @Query("SELECT c FROM Chat c JOIN c.users cu WHERE cu.user = :user")
    List<Chat> findChatsByUsersContaining(UserEntity user);
}
