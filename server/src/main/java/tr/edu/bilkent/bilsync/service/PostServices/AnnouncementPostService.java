package tr.edu.bilkent.bilsync.service.PostServices;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.PostEntities.AnnouncementPost;
import tr.edu.bilkent.bilsync.repository.PostRepositories.AnnouncementPostRepository;

import java.util.List;

@Service
public class AnnouncementPostService {

    private final AnnouncementPostRepository announcementPostRepository;

    public AnnouncementPostService(AnnouncementPostRepository announcementPostRepository) {
        this.announcementPostRepository = announcementPostRepository;
    }

    public boolean createOrSavePost(AnnouncementPost announcementPost) {
        try {
            announcementPostRepository.save(announcementPost);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public List<AnnouncementPost> getPostsSortedByDate() {
        return announcementPostRepository.findAnnouncementPostsSortedByDate();
    }

    public AnnouncementPost getPostByID(long id) {
        return announcementPostRepository.findById(id);
    }

    public void deleteById(long id) {
        announcementPostRepository.deleteById(id);
    }
}