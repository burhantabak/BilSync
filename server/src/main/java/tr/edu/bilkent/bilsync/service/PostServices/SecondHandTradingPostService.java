package tr.edu.bilkent.bilsync.service.PostServices;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.PostEntities.SecondHandTradingPost;
import tr.edu.bilkent.bilsync.repository.PostRepositories.SecondHandTradingPostRepository;

import java.util.List;

@Service
public class SecondHandTradingPostService {

    private final SecondHandTradingPostRepository secondHandTradingPostRepository;

    public SecondHandTradingPostService(SecondHandTradingPostRepository secondHandTradingPostRepository) {
        this.secondHandTradingPostRepository = secondHandTradingPostRepository;
    }

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

    public List<SecondHandTradingPost> getPostsSortedByDate() {
        return secondHandTradingPostRepository.findSecondHandTradingPostsSortedByDate();
    }

    public SecondHandTradingPost getPostByID(long id) {
        return secondHandTradingPostRepository.findById(id);
    }

    public void deleteById(long id) {
        secondHandTradingPostRepository.deleteById(id);
    }

}