package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.LostAndFoundPost;
import tr.edu.bilkent.bilsync.repository.LostAndFoundPostRepository;

import java.util.List;

@Service
public class LostAndFoundPostService {
    private final LostAndFoundPostRepository lostAndFoundPostRepository;

    public LostAndFoundPostService(LostAndFoundPostRepository lostAndFoundPostRepository) {
        this.lostAndFoundPostRepository = lostAndFoundPostRepository;
    }

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

    public List<LostAndFoundPost> getPostsSortedByDate() {
        return lostAndFoundPostRepository.findLostAndFoundPostsSortedByDate();
    }

    public LostAndFoundPost getPostByID(long id) {
        return lostAndFoundPostRepository.findById(id);
    }

    public void deleteById(long id) {
        lostAndFoundPostRepository.deleteById(id);
    }
}
