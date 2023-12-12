package tr.edu.bilkent.bilsync.repository;

import org.springframework.data.repository.CrudRepository;
import tr.edu.bilkent.bilsync.entity.ChatMessage;

public interface ChatMessageRepository extends CrudRepository<ChatMessage, Long> {
}
