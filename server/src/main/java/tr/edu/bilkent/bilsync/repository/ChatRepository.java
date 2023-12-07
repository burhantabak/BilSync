package tr.edu.bilkent.bilsync.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.Chat;
import tr.edu.bilkent.bilsync.entity.UserEntity;

import java.util.List;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Long> {
    List<Chat> findChatsByUsersContaining(UserEntity user);
}
