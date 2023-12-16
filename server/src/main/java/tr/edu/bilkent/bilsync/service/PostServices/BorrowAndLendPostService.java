package tr.edu.bilkent.bilsync.service.PostServices;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.PostEntities.BorrowAndLendPost;
import tr.edu.bilkent.bilsync.repository.PostRepositories.BorrowAndLendPostRepository;

import java.util.List;

@Service
public class BorrowAndLendPostService {
    private final BorrowAndLendPostRepository borrowAndLendPostRepository;

    /**
     * Constructor for BorrowAndLendPostService, injecting the required repository.
     *
     * @param borrowAndLendPostRepository The repository for BorrowAndLendPost entities.
     */
    public BorrowAndLendPostService(BorrowAndLendPostRepository borrowAndLendPostRepository) {
        this.borrowAndLendPostRepository = borrowAndLendPostRepository;
    }

    /**
     * Creates or saves a BorrowAndLendPost entity in the repository.
     *
     * @param borrowAndLendPost The BorrowAndLendPost entity to be created or saved.
     * @return True if the operation is successful, false otherwise.
     */
    public boolean createOrSavePost(BorrowAndLendPost borrowAndLendPost) {
        try {
            borrowAndLendPost.setGiverID(borrowAndLendPost.getAuthorID());
            borrowAndLendPostRepository.save(borrowAndLendPost);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves a list of BorrowAndLendPost entities sorted by date from the repository.
     *
     * @return A list of BorrowAndLendPost entities sorted by date.
     */
    public List<BorrowAndLendPost> getPostsSortedByDate() {
        return borrowAndLendPostRepository.findBorrowAndLendPostsSortedByDate();
    }

    /**
     * Retrieves a BorrowAndLendPost entity by its ID from the repository.
     *
     * @param id The ID of the BorrowAndLendPost entity to retrieve.
     * @return The BorrowAndLendPost entity with the specified ID.
     */
    public BorrowAndLendPost getPostByID(long id) {
        return borrowAndLendPostRepository.findById(id);
    }

    /**
     * Deletes a BorrowAndLendPost entity by its ID from the repository.
     *
     * @param id The ID of the BorrowAndLendPost entity to delete.
     */
    public void deleteById(long id) {
        borrowAndLendPostRepository.deleteById(id);
    }
}
