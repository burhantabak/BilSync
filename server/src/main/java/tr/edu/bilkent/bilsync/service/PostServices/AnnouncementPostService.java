package tr.edu.bilkent.bilsync.service.PostServices;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.PostEntities.AnnouncementPost;
import tr.edu.bilkent.bilsync.repository.PostRepositories.AnnouncementPostRepository;

import java.util.List;

@Service
public class AnnouncementPostService {

    private final AnnouncementPostRepository announcementPostRepository;

    /**
     * Constructor for AnnouncementPostService, injecting the required repository.
     *
     * @param announcementPostRepository The repository for AnnouncementPost entities.
     */
    public AnnouncementPostService(AnnouncementPostRepository announcementPostRepository) {
        this.announcementPostRepository = announcementPostRepository;
    }

    /**
     * Creates or saves an AnnouncementPost entity in the repository.
     *
     * @param announcementPost The AnnouncementPost entity to be created or saved.
     * @return True if the operation is successful, false otherwise.
     */
    public boolean createOrSavePost(AnnouncementPost announcementPost) {
        try {
            announcementPostRepository.save(announcementPost);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves a list of AnnouncementPost entities sorted by date from the repository.
     *
     * @return A list of AnnouncementPost entities sorted by date.
     */
    public List<AnnouncementPost> getPostsSortedByDate() {
        return announcementPostRepository.findAnnouncementPostsSortedByDate();
    }

    /**
     * Retrieves an AnnouncementPost entity by its ID from the repository.
     *
     * @param id The ID of the AnnouncementPost entity to retrieve.
     * @return The AnnouncementPost entity with the specified ID.
     */
    public AnnouncementPost getPostByID(long id) {
        return announcementPostRepository.findById(id);
    }

    /**
     * Deletes an AnnouncementPost entity by its ID from the repository.
     *
     * @param id The ID of the AnnouncementPost entity to delete.
     */
    public void deleteById(long id) {
        announcementPostRepository.deleteById(id);
    }
}
