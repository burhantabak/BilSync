package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.BorrowAndLendPost;
import tr.edu.bilkent.bilsync.repository.BorrowAndLendPostRepository;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class BorrowAndLendPostService {
    private final BorrowAndLendPostRepository borrowAndLendPostRepository;

    public BorrowAndLendPostService(BorrowAndLendPostRepository borrowAndLendPostRepository) {
        this.borrowAndLendPostRepository = borrowAndLendPostRepository;
    }

    public boolean createPost(BorrowAndLendPost borrowAndLendPost) {
        try {
            ZonedDateTime currentTime = ZonedDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedTime = currentTime.format(formatter);
            borrowAndLendPost.setPostDate(formattedTime);
            borrowAndLendPostRepository.save(borrowAndLendPost);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
