package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.LostAndFoundPost;
import tr.edu.bilkent.bilsync.repository.LostAndFoundPostRepository;

@Service
public class LostAndFoundPostService {
    private final LostAndFoundPostRepository lostAndFoundPostRepository;

    public LostAndFoundPostService(LostAndFoundPostRepository lostAndFoundPostRepository) {
        this.lostAndFoundPostRepository = lostAndFoundPostRepository;
    }

    public boolean createPost(LostAndFoundPost lostAndFoundPost) {
        try {
            lostAndFoundPost.setGiverID(lostAndFoundPost.getAuthorID());
            lostAndFoundPostRepository.save(lostAndFoundPost);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}