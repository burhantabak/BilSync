package tr.edu.bilkent.bilsync.service.PostServices;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.PostEntities.SecondHandTradingPost;
import tr.edu.bilkent.bilsync.repository.PostRepositories.SecondHandTradingPostRepository;

import java.util.List;

/**
 * Service class for handling operations related to SecondHandTradingPost entities.
 * It contains methods for creating or saving posts, retrieving posts sorted by date,
 * retrieving a post by its ID, and deleting a post by its ID.
 */
@Service
public class SecondHandTradingPostService {

    private final SecondHandTradingPostRepository secondHandTradingPostRepository;

    /**
     * Constructor for SecondHandTradingPostService, injecting the required repository.
     *
     * @param secondHandTradingPostRepository The repository for SecondHandTradingPost entities.
     */
    public SecondHandTradingPostService(SecondHandTradingPostRepository secondHandTradingPostRepository) {
        this.secondHandTradingPostRepository = secondHandTradingPostRepository;
    }

    /**
     * Creates or saves a SecondHandTradingPost entity in the repository.
     *
     * @param secondHandTradingPost The SecondHandTradingPost entity to be created or saved.
     * @return True if the operation is successful, false otherwise.
     */
    public boolean createOrSavePost(SecondHandTradingPost secondHandTradingPost) {
        try {
            secondHandTradingPost.setGiverID(secondHandTradingPost.getAuthorID());
            secondHandTradingPostRepository.save(secondHandTradingPost);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves a list of SecondHandTradingPost entities sorted by date from the repository.
     *
     * @return A list of SecondHandTradingPost entities sorted by date.
     */
    public List<SecondHandTradingPost> getPostsSortedByDate() {
        return secondHandTradingPostRepository.findSecondHandTradingPostsSortedByDate();
    }

    /**
     * Retrieves a SecondHandTradingPost entity by its ID from the repository.
     *
     * @param id The ID of the SecondHandTradingPost entity to retrieve.
     * @return The SecondHandTradingPost entity with the specified ID.
     */
    public SecondHandTradingPost getPostByID(long id) {
        return secondHandTradingPostRepository.findById(id);
    }

    /**
     * Deletes a SecondHandTradingPost entity by its ID from the repository.
     *
     * @param id The ID of the SecondHandTradingPost entity to delete.
     */
    public void deleteById(long id) {
        secondHandTradingPostRepository.deleteById(id);
    }

}