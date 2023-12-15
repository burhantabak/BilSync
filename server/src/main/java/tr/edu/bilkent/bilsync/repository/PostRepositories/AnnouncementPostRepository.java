package tr.edu.bilkent.bilsync.repository.PostRepositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.PostEntities.AnnouncementPost;

import java.util.List;

@Repository
public interface AnnouncementPostRepository extends CrudRepository<AnnouncementPost, Long> {
    AnnouncementPost findById(long id);
    @Query("SELECT p FROM AnnouncementPost p ORDER BY p.postDate DESC")
    List<AnnouncementPost> findAnnouncementPostsSortedByDate();
    void deleteById(long id);
}
