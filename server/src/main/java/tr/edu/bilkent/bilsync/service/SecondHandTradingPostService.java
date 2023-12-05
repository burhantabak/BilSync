package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.SecondHandTradingPost;
import tr.edu.bilkent.bilsync.repository.SecondHandTradingPostRepository;

@Service
public class SecondHandTradingPostService {

    private final SecondHandTradingPostRepository secondHandTradingPostRepository;

    public SecondHandTradingPostService(SecondHandTradingPostRepository secondHandTradingPostRepository) {
        this.secondHandTradingPostRepository = secondHandTradingPostRepository;
    }

    public boolean createPost(SecondHandTradingPost secondHandTradingPost) {
        try {
            secondHandTradingPost.setGiverID(secondHandTradingPost.getAuthorID());
            secondHandTradingPostRepository.save(secondHandTradingPost);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}