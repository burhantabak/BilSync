package tr.edu.bilkent.bilsync.service.PostServices;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.PostEntities.BorrowAndLendPost;
import tr.edu.bilkent.bilsync.repository.PostRepositories.BorrowAndLendPostRepository;

import java.util.List;

@Service
public class BorrowAndLendPostService {
    private final BorrowAndLendPostRepository borrowAndLendPostRepository;

    public BorrowAndLendPostService(BorrowAndLendPostRepository borrowAndLendPostRepository) {
        this.borrowAndLendPostRepository = borrowAndLendPostRepository;
    }

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

    public List<BorrowAndLendPost> getPostsSortedByDate() {
        return borrowAndLendPostRepository.findBorrowAndLendPostsSortedByDate();
    }

    public BorrowAndLendPost getPostByID(long id) {
        return borrowAndLendPostRepository.findById(id);
    }

    public void deleteById(long id) {
        borrowAndLendPostRepository.deleteById(id);
    }
}
