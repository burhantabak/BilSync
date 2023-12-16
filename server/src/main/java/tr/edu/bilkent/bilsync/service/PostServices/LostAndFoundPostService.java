package tr.edu.bilkent.bilsync.service.PostServices;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.PostEntities.LostAndFoundPost;
import tr.edu.bilkent.bilsync.repository.PostRepositories.LostAndFoundPostRepository;

import java.util.List;

@Service
public class LostAndFoundPostService {
    private final LostAndFoundPostRepository lostAndFoundPostRepository;

    /**
     * Constructor for LostAndFoundPostService, injecting the required repository.
     *
     * @param lostAndFoundPostRepository The repository for LostAndFoundPost entities.
     */
    public LostAndFoundPostService(LostAndFoundPostRepository lostAndFoundPostRepository) {
        this.lostAndFoundPostRepository = lostAndFoundPostRepository;
    }

    /**
     * Creates or saves a LostAndFoundPost entity in the repository.
     *
     * @param lostAndFoundPost The LostAndFoundPost entity to be created or saved.
     * @return True if the operation is successful, false otherwise.
     */
    public boolean createOrSavePost(LostAndFoundPost lostAndFoundPost) {
        try {
            lostAndFoundPost.setGiverID(lostAndFoundPost.getAuthorID());
            lostAndFoundPostRepository.save(lostAndFoundPost);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves a list of LostAndFoundPost entities sorted by date from the repository.
     *
     * @return A list of LostAndFoundPost entities sorted by date.
     */
    public List<LostAndFoundPost> getPostsSortedByDate() {
        return lostAndFoundPostRepository.findLostAndFoundPostsSortedByDate();
    }

    /**
     * Retrieves a LostAndFoundPost entity by its ID from the repository.
     *
     * @param id The ID of the LostAndFoundPost entity to retrieve.
     * @return The LostAndFoundPost entity with the specified ID.
     */
    public LostAndFoundPost getPostByID(long id) {
        return lostAndFoundPostRepository.findById(id);
    }

    /**
     * Deletes a LostAndFoundPost entity by its ID from the repository.
     *
     * @param id The ID of the LostAndFoundPost entity to delete.
     */
    public void deleteById(long id) {
        lostAndFoundPostRepository.deleteById(id);
    }
}
