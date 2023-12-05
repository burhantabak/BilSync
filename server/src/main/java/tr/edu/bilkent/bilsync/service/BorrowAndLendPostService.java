package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.BorrowAndLendPost;
import tr.edu.bilkent.bilsync.repository.BorrowAndLendPostRepository;

@Service
public class BorrowAndLendPostService {
    private final BorrowAndLendPostRepository borrowAndLendPostRepository;

    public BorrowAndLendPostService(BorrowAndLendPostRepository borrowAndLendPostRepository) {
        this.borrowAndLendPostRepository = borrowAndLendPostRepository;
    }

    public boolean createPost(BorrowAndLendPost borrowAndLendPost) {
        try {
            borrowAndLendPost.setGiverID(borrowAndLendPost.getAuthorID());
            borrowAndLendPostRepository.save(borrowAndLendPost);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
