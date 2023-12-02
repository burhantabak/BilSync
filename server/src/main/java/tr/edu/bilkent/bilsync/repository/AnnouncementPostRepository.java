package tr.edu.bilkent.bilsync.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.AnnouncementPost;

@Repository
public interface AnnouncementPostRepository extends CrudRepository<AnnouncementPost, Long> {
    AnnouncementPost findById(long id);
}
