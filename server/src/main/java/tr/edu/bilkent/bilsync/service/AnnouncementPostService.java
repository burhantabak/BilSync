package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.AnnouncementPost;
import tr.edu.bilkent.bilsync.repository.AnnouncementPostRepository;

import java.util.List;

@Service
public class AnnouncementPostService {

    private final AnnouncementPostRepository announcementPostRepository;

    public AnnouncementPostService(AnnouncementPostRepository announcementPostRepository) {
        this.announcementPostRepository = announcementPostRepository;
    }

    public boolean createPost(AnnouncementPost announcementPost) {
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
}
