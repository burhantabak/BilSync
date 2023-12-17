package tr.edu.bilkent.bilsync.service.PostServices;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.PostEntities.SectionExchangePost;
import tr.edu.bilkent.bilsync.repository.PostRepositories.SectionExchangePostRepository;

import java.util.List;

/**
 * Service class for handling operations related to SectionExchangePost entities.
 * It contains methods for creating or saving posts, retrieving posts sorted by date,
 * retrieving a post by its ID, and deleting a post by its ID.
 */
@Service
public class SectionExchangePostService {

    private final SectionExchangePostRepository sectionExchangePostRepository;

    /**
     * Constructor for SectionExchangePostService, injecting the required repository.
     *
     * @param sectionExchangePostRepository The repository for SectionExchangePost entities.
     */
    public SectionExchangePostService(SectionExchangePostRepository sectionExchangePostRepository) {
        this.sectionExchangePostRepository = sectionExchangePostRepository;
    }

    /**
     * Creates or saves a SectionExchangePost entity in the repository.
     *
     * @param sectionExchangePost The SectionExchangePost entity to be created or saved.
     * @return True if the operation is successful, false otherwise.
     */
    public boolean createOrSavePost(SectionExchangePost sectionExchangePost) {
        try {
            sectionExchangePost.setGiverID(sectionExchangePost.getAuthorID());
            sectionExchangePostRepository.save(sectionExchangePost);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves a list of SectionExchangePost entities sorted by date from the repository.
     *
     * @return A list of SectionExchangePost entities sorted by date.
     */
    public List<SectionExchangePost> getPostsSortedByDate() {
        return sectionExchangePostRepository.findSectionExchangePostsSortedByDate();
    }

    /**
     * Retrieves a SectionExchangePost entity by its ID from the repository.
     *
     * @param id The ID of the SectionExchangePost entity to retrieve.
     * @return The SectionExchangePost entity with the specified ID.
     */
    public SectionExchangePost getPostByID(long id) {
        return sectionExchangePostRepository.findById(id);
    }

    /**
     * Deletes a SectionExchangePost entity by its ID from the repository.
     *
     * @param id The ID of the SectionExchangePost entity to delete.
     */
    public void deleteById(long id) {
        sectionExchangePostRepository.deleteById(id);
    }
}