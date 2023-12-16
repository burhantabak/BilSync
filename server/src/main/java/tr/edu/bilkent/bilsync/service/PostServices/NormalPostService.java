package tr.edu.bilkent.bilsync.service.PostServices;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.PostEntities.NormalPost;
import tr.edu.bilkent.bilsync.repository.PostRepositories.NormalPostRepository;

import java.util.List;

@Service
public class NormalPostService {

    private final NormalPostRepository normalPostRepository;

    /**
     * Constructor for NormalPostService, injecting the required repository.
     *
     * @param normalPostRepository The repository for NormalPost entities.
     */
    public NormalPostService(NormalPostRepository normalPostRepository) {
        this.normalPostRepository = normalPostRepository;
    }

    /**
     * Creates or saves a NormalPost entity in the repository.
     *
     * @param normalPost The NormalPost entity to be created or saved.
     * @return True if the operation is successful, false otherwise.
     */
    public boolean createOrSavePost(NormalPost normalPost) {
        try {
            normalPostRepository.save(normalPost);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves a list of NormalPost entities sorted by date from the repository.
     *
     * @return A list of NormalPost entities sorted by date.
     */
    public List<NormalPost> getPostsSortedByDate() {
        return normalPostRepository.findNormalPostsSortedByDate();
    }

    /**
     * Retrieves a NormalPost entity by its ID from the repository.
     *
     * @param id The ID of the NormalPost entity to retrieve.
     * @return The NormalPost entity with the specified ID.
     */
    public NormalPost getPostByID(long id) {
        return normalPostRepository.findById(id);
    }

    /**
     * Deletes a NormalPost entity by its ID from the repository.
     *
     * @param id The ID of the NormalPost entity to delete.
     */
    public void deleteById(long id) {
        normalPostRepository.deleteById(id);
    }
}
